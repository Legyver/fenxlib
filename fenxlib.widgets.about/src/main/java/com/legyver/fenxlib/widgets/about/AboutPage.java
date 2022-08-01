package com.legyver.fenxlib.widgets.about;

import com.legyver.fenxlib.core.controls.popup.Popup;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.Properties;

/**
 * Widget to create an 'About' page for an application
 */
public class AboutPage extends Popup {

	private final String intro;
	private final String gist;
	private final String additionalInfo;
	private final String version;
	private final String openSourceTagLine;
	private final String buildDate;
	private final String copyright;
	private final Properties licenseProperties;

	/**
	 * Construct an About Page
	 * @param intro the first paragraph or property containing the first paragraph for i18n purposes
	 * @param gist the second paragraph or property containing the second paragraph for i18n purposes
	 * @param additionalInfo the third paragraph or property containing the third paragraph for i18n purposes
	 * @param version the version of the application
	 * @param buildDate the build-date of the application
	 * @param copyright the copyright notice for the application
	 * @param openSourceTagLine the tagline to display before list of open source libraries or property containing the tagline for i18n purposes
	 * @param licenseProperties properties file containing any additional license information for libraries
	 */
	public AboutPage(String intro, String gist, String additionalInfo, String version, String buildDate, String copyright, String openSourceTagLine, Properties licenseProperties) {
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		this.version = version;
		this.openSourceTagLine = openSourceTagLine;
		this.buildDate = buildDate;
		this.copyright = copyright;
		this.licenseProperties = licenseProperties;
		getStyleClass().add("about-page");
	}

	/**
	 * Construct an about page
	 * @param intro the first paragraph or property containing the first paragraph for i18n purposes
	 * @param gist the second paragraph or property containing the second paragraph for i18n purposes
	 * @param additionalInfo the third paragraph or property containing the third paragraph for i18n purposes
	 * @param version the version of the application
	 * @param buildDate the build-date of the application
	 * @param copyright the copyright notice for the application
	 * @param licenseProperties properties file containing any additional license information for libraries
	 */
	public AboutPage(String intro, String gist, String additionalInfo, String version, String buildDate, String copyright, Properties licenseProperties) {
		this(intro, gist, additionalInfo, version, buildDate, copyright, "legyver.defaults.label.about.powered.by.clause", licenseProperties);
	}

	@Override
	public Skin<?> createDefaultSkin() {
		return new AboutPageSkin(this);
	}

	@Override
	public String getUserAgentStylesheet() {
		return AboutPage.class.getResource("aboutpage.css").toExternalForm();
	}

	/**
	 * Get the first paragraph
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * Get the second paragraph
	 * @return the gist
	 */
	public String getGist() {
		return gist;
	}

	/**
	 * Get the tagline to display prior to the list of open-source library dependencies
	 * @return the tagline
	 */
	public String getOpenSourceTagLine() {
		return openSourceTagLine;
	}

	/**
	 * Get the third paragraph
	 * @return any additional information
	 */
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Get the version of the application
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Get the build-date of the application
	 * @return the build-date
	 */
	public String getBuildDate() {
		return buildDate;
	}

	/**
	 * Get the copyright notice for the application
	 * @return the copyright notice
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * Get the properties file containing additional license information for any open-source libraries used
	 * @return the license properties file
	 */
	public Properties getLicenseProperties() {
		return licenseProperties;
	}

	@Override
	public boolean equals(Object obj) {//only one about page makes sense
		return obj instanceof AboutPage;
	}
}
