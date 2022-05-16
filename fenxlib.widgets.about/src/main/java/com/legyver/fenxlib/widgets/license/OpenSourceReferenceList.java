package com.legyver.fenxlib.widgets.license;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Render list of libraries used
 */
public class OpenSourceReferenceList extends Control {
	private static final String PROPERTY_LINK = ".link.\\d$";
	private static final Pattern linkPattern = Pattern.compile(PROPERTY_LINK);
	private final List<Item> items = new ArrayList<>();

	/**
	 * Construct and Open source reference list based on some properties
	 * @param openSourceProperties properties including license information
	 */
	public OpenSourceReferenceList(Properties openSourceProperties) {
		parseItems(openSourceProperties);
		getStyleClass().add("open-source-reference-list");
	}

	private void parseItems(Properties licenseProperties) {
		Item item = null;
		Comparator<String> comparator = (s, t1) -> s.compareTo(t1);
		SortedSet<String> propertyNames = new TreeSet<>(comparator);
		propertyNames.addAll(licenseProperties.stringPropertyNames());

		for (String propertyName : propertyNames) {
			String propertyValue = licenseProperties.getProperty(propertyName);
			Matcher m = linkPattern.matcher(propertyName);
			if (m.find()) {
				item.licenseLinks.add(propertyValue);
			} else {
				if (item != null) {
					items.add(item);
				}
				item = new Item(propertyName);
				if (propertyValue.contains("/")) {//dual licenses ex: GPL/CC BY 4.0
					String[] licenses = propertyValue.split("/");
					for (String license : licenses) {
						item.licenseNames.add(license);
					}
				} else {
					item.licenseNames.add(propertyValue);
				}
			}
		}
		//add last item constructed (since we're otherwise only adding the previous item when the following one is found)
		if (item != null) {
			items.add(item);
		}

	}

	@Override
	public Skin<?> createDefaultSkin() {
		return new OpenSourceReferenceListSkin(this);
	}

	@Override
	public String getUserAgentStylesheet() {
		return OpenSourceReferenceList.class.getResource("opensourcereferencelist.css").toExternalForm();
	}

	/**
	 * Get items to render on the open source reference list
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * An open-source library to be included as item in reference list
	 */
	public class Item {
		private List<String> licenseLinks = new ArrayList<>();
		private List<String> licenseNames = new ArrayList<>();
		private final String artifact;

		/**
		 * Construct an open source reference item based on its artifact
		 * @param artifact the artifact
		 */
		public Item(String artifact) {
			this.artifact = artifact;
		}

		/**
		 * Get license links
		 * @return the license links
		 */
		public List<String> getLicenseLinks() {
			return licenseLinks;
		}

		/**
		 * Get license names
		 * @return the license names
		 */
		public List<String> getLicenseNames() {
			return licenseNames;
		}

		/**
		 * Get artifact
		 * @return the artifact
		 */
		public String getArtifact() {
			return artifact;
		}
	}
}
