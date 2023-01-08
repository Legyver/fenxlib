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


	/**
	 * Construct an AboutDetails widget
	 * @param version the version of the application
	 * @param buildDate the build-date of the application
	 * @param intro the first paragraph for the about section
	 * @param gist the second paragraph for the about section
	 * @param additionalInfo the third paragraph for the about section
	 */
	public AboutDetails(String version, String buildDate, String intro, String gist, String additionalInfo) {
		this.version = version;
		this.buildDate = buildDate;
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		getStyleClass().add("about-details");
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

	@Override
	public String getUserAgentStylesheet() {
		return AboutDetails.class.getResource("aboutdetails.css").toExternalForm();
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new AboutDetailsSkin(this);
	}



}
