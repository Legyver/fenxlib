package com.legyver.fenxlib.widget.about;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CopyrightNoticeSkin extends SkinBase<CopyrightNotice> {
	private final StackPane content;

	public CopyrightNoticeSkin(CopyrightNotice copyrightNotice) {
		super(copyrightNotice);
		content = new StackPane();
		content.getChildren().add(new Text(copyrightNotice.getText()));
		content.getStyleClass().add("text");
		getChildren().addAll(content);
	}
}
