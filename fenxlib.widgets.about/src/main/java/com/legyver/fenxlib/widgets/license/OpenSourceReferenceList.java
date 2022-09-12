package com.legyver.fenxlib.widgets.license;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Render list of libraries used
 */
public class OpenSourceReferenceList extends Control {
	private static final Logger logger = LogManager.getLogger(OpenSourceReferenceList.class);

	private static final String PROPERTY_LINK = ".link.\\d$";
	private static final Pattern linkPattern = Pattern.compile(PROPERTY_LINK);
	private final List<DependencyData> items;


	/**
	 * Construct and Open source reference list based on some properties
	 * @param openSourceProperties properties including license information
	 */
	public OpenSourceReferenceList(Properties openSourceProperties) {
		List<DependencyData> items1;
		try {
			items1 = new LicensePropertyParser(openSourceProperties).parseItems();
		} catch (UnknownLicenseMetadataException e) {
			logger.error("Error parsing license properties", e);
			items1 = Collections.EMPTY_LIST;
		}
		items = items1;
		getStyleClass().add("open-source-reference-list");
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
	public List<DependencyData> getItems() {
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
