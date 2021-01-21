package com.legyver.fenxlib.widget.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class AboutBlurb extends Control {
	private final String intro;
	private final String gist;
	private final String additionalInfo;

	public AboutBlurb(String intro, String gist, String additionalInfo) {
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		getStyleClass().add("about-blurb");
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

	@Override
	public String getUserAgentStylesheet() {
		return AboutBlurb.class.getResource("aboutblurb.css").toExternalForm();
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new AboutBlurbSkin(this);
	}
}
