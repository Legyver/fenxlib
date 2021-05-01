package com.legyver.fenxlib.widgets.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Widget to render details about an application
 */
public class AboutDetails extends Control {
	private final String version;
	private final String buildDate;

	private final String intro;
	private final String gist;
	private final String additionalInfo;

	private final String openSourceTagLine;

	/**
	 * Construct an AboutDetails widget
	 * @param version the version of the application
	 * @param buildDate the build-date of the application
	 * @param intro the first paragraph for the about section
	 * @param gist the second paragraph for the about section
	 * @param additionalInfo the third paragraph for the about section
	 * @param openSourceTagLine the header to use (ex: 'Powered by open-source')
	 */
	public AboutDetails(String version, String buildDate, String intro, String gist, String additionalInfo, String openSourceTagLine) {
		this.version = version;
		this.buildDate = buildDate;
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		this.openSourceTagLine = openSourceTagLine;
//		getStyleClass().add("about-details");
	}

	/**
	 * Get the application version
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Get the application build-date
	 * @return the build date
	 */
	public String getBuildDate() {
		return buildDate;
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
	 * Get the third paragraph
	 * @return any additional info
	 */
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Get the tagline to precede the open source library list
	 * @return the tagline
	 */
	public String getOpenSourceTagLine() {
		return openSourceTagLine;
	}
//
//	@Override
//	public String getUserAgentStylesheet() {
//		return AboutDetails.class.getResource("aboutdetails.css").toExternalForm();
//	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new AboutDetailsSkin(this);
	}



}
