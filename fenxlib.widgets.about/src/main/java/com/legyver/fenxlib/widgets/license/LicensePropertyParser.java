package com.legyver.fenxlib.widgets.license;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for property files to populate the list of library dependencies.
 */
public class LicensePropertyParser {
    private static final String V1_PROPERTY_LINK = "^((([a-zA-Z0-9\\.-])+)(.link.)((\\d)+))$";
    private static final Pattern v1LinkPattern = Pattern.compile(V1_PROPERTY_LINK);
    private static final String V2_METADATA_LIST_TEXT_OR_LINK = "^((([a-zA-Z0-9\\.-])+)(.license.metadata.)(([a-zA-Z0-9-])+)(\\[((\\d)+)\\]\\.)(text|link))$";
    private static final Pattern v2MetadataListTextOrLinkPattern = Pattern.compile(V2_METADATA_LIST_TEXT_OR_LINK);
    private static final String V2_METADATA_LIST_VALUE = "^((([a-zA-Z0-9\\.-])+)(.license.metadata.)(([a-zA-Z0-9-])+)(\\[((\\d)+)\\]))$";
    private static final Pattern v2MetadataListValuePattern = Pattern.compile(V2_METADATA_LIST_VALUE);
    private static final String V2_METADATA_NAME_OR_RETRIEVED = "^((([a-zA-Z0-9\\.-])+)(.license.metadata.)(name|retrieved))$";
    private static final Pattern v2MetadataNameOrRetrievedPattern = Pattern.compile(V2_METADATA_NAME_OR_RETRIEVED);

    private final Properties licenseProperties;

    /**
     * Construct a parser for the indicated property file
     * @param licenseProperties the properties to parse
     */
    public LicensePropertyParser(Properties licenseProperties) {
        this.licenseProperties = licenseProperties;
    }

    /**
     * Parse the property file into the equivalent dependency data
     * @return the list of dependency data
     * @throws UnknownLicenseMetadataException if there is an unknown metadata type
     */
    public List<DependencyData> parseItems() throws UnknownLicenseMetadataException {
        Map<String, DependencyData> items = new HashMap<>();
        SortedSet<String> propertyNames = new TreeSet<>(String::compareTo);
        propertyNames.addAll(licenseProperties.stringPropertyNames());

        for (String propertyName : propertyNames) {
            String propertyValue = licenseProperties.getProperty(propertyName);
            if (propertyValue != null && !propertyValue.isEmpty() && !propertyValue.isBlank()) {
                Matcher v1Link = v1LinkPattern.matcher(propertyName);
                Matcher v2TextOrLinkList = v2MetadataListTextOrLinkPattern.matcher(propertyName);
                Matcher v2ValueList = v2MetadataListValuePattern.matcher(propertyName);

                Matcher v2Value = v2MetadataNameOrRetrievedPattern.matcher(propertyName);
                if (v2TextOrLinkList.find()) {
                    processV2ListTyped(items, propertyValue, v2TextOrLinkList);
                } else if (v2ValueList.find()) {
                    processV2ListUnTyped(items, propertyValue, v2ValueList);
                } else if (v2Value.find()) {
                    processV2Value(items, propertyValue, v2Value);
                } else if (v1Link.find()) {
                    processV1Link(items, propertyValue, v1Link);
                } else {
                    processV1Value(items, propertyName, propertyValue);
                }
            }
        }
        List<DependencyData> dataList = new ArrayList<>(items.values());
        dataList.sort(Comparator.comparing(DependencyData::getName));
        return dataList;
    }

    private void processV1Value(Map<String, DependencyData> items, String propertyName, String propertyValue) {
        DependencyData dependencyData = items.computeIfAbsent(propertyName, x -> new DependencyData());
        dependencyData.setName(propertyName);
        DependencyData.TextLink metadata = dependencyData.getCopyright(0);
        metadata.setText(propertyValue);
    }

    private void processV1Link(Map<String, DependencyData> items, String propertyValue, Matcher v1Link) {
        String name = v1Link.group(2);
        String index = v1Link.group(5);
        DependencyData dependencyData = items.computeIfAbsent(name, x -> new DependencyData());
        DependencyData.TextLink metadata = dependencyData.getCopyright(Integer.valueOf(index));
        metadata.setLink(propertyValue);
    }

    private void processV2Value(Map<String, DependencyData> items, String propertyValue, Matcher v2Value) {
        String name = v2Value.group(2);
        String metadataType = v2Value.group(5);
        DependencyData dependencyData = items.computeIfAbsent(name, x -> new DependencyData());
        dependencyData.setVersion(DependencyData.Version.V2);
        if ("retrieved".equals(metadataType)) {
            dependencyData.setRetrieved(LocalDate.parse(propertyValue));
        } else if ("name".equals(metadataType)) {
            dependencyData.setName(propertyValue);
        }
    }

    private void processV2ListUnTyped(Map<String, DependencyData> items, String propertyValue, Matcher v2) throws UnknownLicenseMetadataException {
        String name = v2.group(2);
        String metadataType = v2.group(5);
        String index = v2.group(8);
        processV2List(items, propertyValue, name, metadataType, index, "text");
    }

    private void processV2ListTyped(Map<String, DependencyData> items, String propertyValue, Matcher v2) throws UnknownLicenseMetadataException {
        String name = v2.group(2);
        String metadataType = v2.group(5);
        String index = v2.group(8);
        String textLinkType = v2.group(10);
        processV2List(items, propertyValue, name, metadataType, index, textLinkType);
    }

    private void processV2List(Map<String, DependencyData> items, String propertyValue, String name, String metadataType, String index, String type) throws UnknownLicenseMetadataException {
        DependencyData dependencyData = items.computeIfAbsent(name, x -> new DependencyData());
        dependencyData.setVersion(DependencyData.Version.V2);
        DependencyData.OrderedText metadata = null;
        switch (metadataType) {
            case "author" :
                metadata = dependencyData.getAuthor(Integer.valueOf(index));
                break;
            case "title":
                metadata = dependencyData.getTitle(Integer.valueOf(index));
                break;
            case "disclaimer":
                metadata =  dependencyData.getDisclaimer(Integer.valueOf(index));
                break;
            case "copyright":
                metadata = dependencyData.getCopyright(Integer.valueOf(index));
                break;
            case "change":
                metadata = dependencyData.getChange(Integer.valueOf(index));
                break;
            default:
                throw new UnknownLicenseMetadataException("Unknown metadata type: " + metadataType);
        }
        metadata.accept(type, propertyValue);
    }
}
