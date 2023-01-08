package com.legyver.fenxlib.widgets.license;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

/**
 * Data about the library dependencies and their respective licenses
 */
public class DependencyData {
    private Version version= Version.V1;
    private String name;
    private LocalDate retrieved;
    private final Map<Integer, TextLink> authors = new HashMap<>();
    private final Map<Integer, TextLink> titles = new HashMap<>();
    private final Map<Integer, TextLink> disclaimers = new HashMap<>();
    private final Map<Integer, TextLink> licenses = new HashMap<>();
    private final Map<Integer, OrderedText> changes = new HashMap<>();

    /**
     * Set the version when known
     * @param version the version
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * Get the name of the library
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the library
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the retrieved date
     * @return the retrieved date
     */
    public LocalDate getRetrieved() {
        return retrieved;
    }

    /**
     * Set the retrieved date
     * @param retrieved the retrieved date
     */
    public void setRetrieved(LocalDate retrieved) {
        this.retrieved = retrieved;
    }

    /**
     * Get the list of changes
     * @return the changes
     */
    public List<OrderedText> getChanges() {
        return sortedList(changes);
    }

    /**
     * Get the list of authors
     * @return the authors
     */
    public List<TextLink> getAuthors() {
        return sortedList(authors);
    }

    /**
     * Get the list of titles
     * @return the list of titles
     */
    public List<TextLink> getTitles() {
        return sortedList(titles);
    }

    /**
     * Get the copyrights
     * @return the copyrights
     */
    public List<TextLink> getLicenses() {
        return sortedList(licenses);
    }

    private <T extends OrderedText> List<T> sortedList(Map<Integer, T> textLinkMap) {
        List<T> result = new ArrayList(textLinkMap.values());
        result.sort(Comparator.comparing(o -> o.order));
        return result;
    }

    private <T extends OrderedText> T getOrDefault(Integer index, Map<Integer, T> textLinkMap, Function<Integer, T> creator) {
        T result = textLinkMap.get(index);
        if (result == null) {
            result = creator.apply(index);
            textLinkMap.put(result.order, result);
        }
        return result;
    }

    private TextLink getOrDefault(Integer index, Map<Integer, DependencyData.TextLink> textLinkMap) {
        return getOrDefault(index, textLinkMap, x -> new DependencyData.TextLink(index));
    }

    /**
     * Get the author at the index.
     * If there is no existing author one will be created for you and added to the collection.
     * @param index the index
     * @return the existing or new author
     */
    public TextLink getAuthor(Integer index) {
        return getOrDefault(index, authors);
    }

    /**
     * Get the title at the index.
     * If there is no existing title one will be created for you and added to the collection.
     * @param index the index
     * @return the existing or new title
     */
    public TextLink getTitle(Integer index) {
        return getOrDefault(index, titles);
    }

    /**
     * Get a particular license at an index.
     * If there is no existing license at that index, one will be created for you and added to the collection.
     * @param index the index
     * @return the existing or new license data
     */
    public TextLink getLicense(Integer index) {
        return getOrDefault(index, licenses);
    }

    /**
     * Get the change at the index.
     * If there is no existing change one will be created for you and added to the collection.
     * @param index the index
     * @return the existing or new change
     */
    public OrderedText getChange(int index) {
        return getOrDefault(index, changes, x -> new OrderedText(index));
    }

    /**
     * Text associated with an index
     */
    public class OrderedText {
        String text;
        final Integer order;

        /**
         * Construct ordered text
         * @param order the order (index) of the text
         */
        public OrderedText(Integer order) {
            this.order = order;
        }

        /**
         * Get the text
         * @return the text
         */
        public String getText() {
            return text;
        }

        /**
         * Set the text
         * @param text the text
         */
        public void setText(String text) {
            version.setText(DependencyData.this, this, text);
        }

        /**
         * Set the value as required by the property type.
         * Supported types are "text".  Unsupported types have no effect
         * @param textLinkType the type of the value
         * @param propertyValue the text
         */
        public void accept(String textLinkType, String propertyValue) {
            if ("text".equals(textLinkType)) {
                text = propertyValue;
            }
        }
    }

    /**
     * Text associated with a link
     */
    public class TextLink extends OrderedText {

        private String link;

        /**
         * Construct a text link reference with the appropriate order
         * @param order the order (index) of the text/link
         */
        public TextLink(Integer order) {
            super(order);
        }

        /**
         * Get the link
         * @return the link
         */
        public String getLink() {
            return link;
        }

        /**
         * Set the link
         * @param link the link
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         * Set the property value as required by the property type.
         * Supported types are "link" and those supported by {@link OrderedText#accept(String, String)}.  Unsupported types have no effect.
         * @param textLinkType the type of the value
         * @param propertyValue the value to set
         */
        public void accept(String textLinkType, String propertyValue) {
            super.accept(textLinkType, propertyValue);
            if ("link".equals(textLinkType)) {
                link = propertyValue;
            }
        }
    }

    /**
     * The version of the dependency data type
     */
    public enum Version {
        /**
         * Version one.
         * This separated multiple license names with a '/' and then allowed multiple corresponding links to be added after.
         * Example:
         * io.icomoon=GPL/CC BY 4.0
         * io.icomoon.link.0=http://www.gnu.org/licenses/gpl.html
         * io.icomoon.link.1=http://creativecommons.org/licenses/by/4.0/
         */
        V1 {
            @Override
            void setText(DependencyData dependencyData, OrderedText orderedText, String value) {
                if (value.contains("/")) {
                    String[] parts = value.split("/");
                    for (int i = 0; i < parts.length; i++) {
                        String part = parts[i];
                        int dest = i + orderedText.order;
                        TextLink textLink = dependencyData.getLicense(dest);
                        textLink.text = part;
                    }
                } else {
                    orderedText.text = value;
                }
            }
        },
        /**
         * Version two.
         * Allows for the capture of more complete license data.
         * This supports
         * - simple property values ('name', 'retried')
         * - lists of text data ('change')
         * - lists of linked data ('author', 'title', 'disclaimer', 'copyright')
         * Example:
         * io.icomoon.license.metadata.name=IcoMoon
         * io.icomoon.license.metadata.retrieved=2022-04-16
         * io.icomoon.license.metadata.author[0].text=IcoMoon
         * io.icomoon.license.metadata.author[0].link=https://icomoon.io/
         * io.icomoon.license.metadata.title[0].text=IcoMoon - Free
         * io.icomoon.license.metadata.title[0].link=https://icomoon.io/#icons-icomoon
         * io.icomoon.license.metadata.disclaimer[0].text=Not a real disclaimer.
         * io.icomoon.license.metadata.copyright[0].text=GPL
         * io.icomoon.license.metadata.copyright[0].link=http://www.gnu.org/licenses/gpl.html
         * io.icomoon.license.metadata.copyright[1].text=CC BY 4.0
         * io.icomoon.license.metadata.copyright[1].link=http://creativecommons.org/licenses/by/4.0/
         * io.icomoon.license.metadata.change[0]=No change.
         */
        V2 {
            @Override
            void setText(DependencyData dependencyData, OrderedText orderedText, String value) {
                orderedText.text = value;
            }
        };

        /**
         * Set the text
         * @param dependencyData the parent dependency data reference
         * @param orderedText the node to set the text on
         * @param value the value to set
         */
        abstract void setText(DependencyData dependencyData, OrderedText orderedText, String value);
    }
}
