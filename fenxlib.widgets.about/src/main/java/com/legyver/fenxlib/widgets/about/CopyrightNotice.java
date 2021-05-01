package com.legyver.fenxlib.widgets.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Widget to render the copyright notice
 */
public class CopyrightNotice extends Control {
	private final String text;

	/**
	 * Construct a copyright notice with a given set of text
	 * @param text the copyright text
	 */
	public CopyrightNotice(String text) {
		this.text = text;
		getStyleClass().add("copyright-notice");
	}

	/**
	 * Get text
	 * @return copyright text
	 */
	public String getText() {
		return text;
	}

	@Override
	public String getUserAgentStylesheet() {
		return CopyrightNotice.class.getResource("copyrightnotice.css").toExternalForm();
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new CopyrightNoticeSkin(this);
	}
}
