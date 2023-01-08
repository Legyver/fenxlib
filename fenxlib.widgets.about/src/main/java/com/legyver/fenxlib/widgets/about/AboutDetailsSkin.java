package com.legyver.fenxlib.widgets.about;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.commons.lang3.StringUtils;

import static com.legyver.fenxlib.api.controls.utils.TextFactoryUtils.getText;

/**
 * Skin for the AboutDetails widget
 */
public class AboutDetailsSkin extends SkinBase<AboutDetails> {
	private final StackPane buildStack;

	/**
	 * Construct a skin for the about details
	 * @param aboutDetails the details to skin
	 */
	public AboutDetailsSkin(AboutDetails aboutDetails) {
		super(aboutDetails);
		VBox vBox = new VBox();
		vBox.setSpacing(32);

		buildStack = new StackPane();
		buildStack.getStyleClass().add("build");
		if (!StringUtils.isEmpty(aboutDetails.getVersion())) {
			buildStack.getChildren().add(getBuildFlow(aboutDetails));
			vBox.getChildren().add(buildStack);
		}

		AboutBlurb aboutBlurb = new AboutBlurb(aboutDetails.getIntro(), aboutDetails.getGist(), aboutDetails.getAdditionalInfo());
		vBox.getChildren().add(aboutBlurb);

		getChildren().add(vBox);
	}

	private TextFlow getBuildFlow(AboutDetails aboutDetails) {
		TextFlow buildFlow = new TextFlow();
		buildFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
		buildFlow.getChildren().add(getText("legyver.defaults.label.about.build.message", aboutDetails.getVersion(), aboutDetails.getBuildDate()));
		return buildFlow;
	}
}
