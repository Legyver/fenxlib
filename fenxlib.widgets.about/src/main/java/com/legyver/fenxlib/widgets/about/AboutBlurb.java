package com.legyver.fenxlib.widgets.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * A widget that displays the about description for an application
 */
public class AboutBlurb extends Control {
	private final String intro;
	private final String gist;
	private final String additionalInfo;

	/**
	 * Construct an AboutBlurb
	 * @param intro the first paragraph
	 * @param gist the second paragraph
	 * @param additionalInfo the third paragraph
	 */
	public AboutBlurb(String intro, String gist, String additionalInfo) {
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		getStyleClass().add("about-blurb");
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
	 * @return the additional info
	 */
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
