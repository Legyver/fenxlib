package com.legyver.fenxlib.widget.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.Properties;

public class AboutPage extends Control {

	private final String intro;
	private final String gist;
	private final String additionalInfo;
	private final String version;
	private final String openSourceTagLine;
	private final String buildDate;
	private final String copyright;
	private final Properties licenseProperties;

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

	public AboutPage(String intro, String gist, String additionalInfo, String version, String buildDate, String copyright, Properties licenseProperties) {
		this(intro, gist, additionalInfo, version, buildDate, copyright, "Powered by open source", licenseProperties);
	}

	@Override
	public Skin<?> createDefaultSkin() {
		return new AboutPageSkin(this);
	}

	@Override
	public String getUserAgentStylesheet() {
		return AboutPage.class.getResource("aboutpage.css").toExternalForm();
	}

	public String getIntro() {
		return intro;
	}

	public String getGist() {
		return gist;
	}

	public String getOpenSourceTagLine() {
		return openSourceTagLine;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public String getVersion() {
		return version;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public String getCopyright() {
		return copyright;
	}

	public Properties getLicenseProperties() {
		return licenseProperties;
	}

	@Override
	public boolean equals(Object obj) {//only one about page makes sense
		return obj instanceof AboutPage;
	}
}
