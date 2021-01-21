package com.legyver.fenxlib.widget.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class AboutDetails extends Control {
	private final String version;
	private final String buildDate;

	private final String intro;
	private final String gist;
	private final String additionalInfo;

	private final String openSourceTagLine;

	public AboutDetails(String version, String buildDate, String intro, String gist, String additionalInfo, String openSourceTagLine) {
		this.version = version;
		this.buildDate = buildDate;
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		this.openSourceTagLine = openSourceTagLine;
//		getStyleClass().add("about-details");
	}

	public String getVersion() {
		return version;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public String getIntro() {
		return intro;
	}

	public String getGist() {
		return gist;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

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
