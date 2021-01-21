package com.legyver.fenxlib.widget.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class CopyrightNotice extends Control {
	private final String text;

	public CopyrightNotice(String text) {
		this.text = text;
		getStyleClass().add("copyright-notice");
	}

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
