package com.legyver.fenxlib.widget.about;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.commons.lang3.StringUtils;

public class AboutBlurbSkin extends SkinBase<AboutBlurb> {
	private final StackPane intro;
	private final StackPane gist;
	private final StackPane additionalInfo;

	public AboutBlurbSkin(AboutBlurb aboutBlurb) {
		super(aboutBlurb);
		VBox aboutBox = new VBox();
		aboutBox.setSpacing(10);

		intro = new StackPane();
		intro.getStyleClass().add("intro");
		if (!StringUtils.isEmpty(aboutBlurb.getIntro())) {
			intro.getChildren().add(new TextFlow(new Text(aboutBlurb.getIntro())));
			aboutBox.getChildren().add(intro);
		}

		gist = new StackPane();
		gist.getStyleClass().add("gist");
		if (!StringUtils.isEmpty(aboutBlurb.getGist())) {
			gist.getChildren().add(new TextFlow(new Text(aboutBlurb.getGist())));
			aboutBox.getChildren().add(gist);
		}

		additionalInfo = new StackPane();
		additionalInfo.getStyleClass().add("additional-info");
		if (!StringUtils.isEmpty(aboutBlurb.getAdditionalInfo())) {
			additionalInfo.getChildren().add(new TextFlow(new Text(aboutBlurb.getAdditionalInfo())));
			aboutBox.getChildren().add(additionalInfo);
		}
		getChildren().add(aboutBox);
	}
}
