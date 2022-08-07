package com.legyver.fenxlib.widgets.about;

import com.legyver.fenxlib.api.controls.factory.TextFactoryMixin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.commons.lang3.StringUtils;

/**
 * Skin for the AboutDetails widget
 */
public class AboutDetailsSkin extends SkinBase<AboutDetails> implements TextFactoryMixin {
	private final StackPane buildStack;
	private final StackPane openSourceTaglineStack;

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

		openSourceTaglineStack = new StackPane();
		openSourceTaglineStack.getStyleClass().add("open-source-tagline");
		openSourceTaglineStack.getChildren().add(getPowerFlow(aboutDetails));
		vBox.getChildren().add(openSourceTaglineStack);
		
		getChildren().add(vBox);
	}

	private TextFlow getPowerFlow(AboutDetails aboutDetails) {
		String poweredByClause = aboutDetails.getOpenSourceTagLine();
		Text textPoweredBy = getText(poweredByClause);
		textPoweredBy.setId("powered-by");
//		textPoweredBy.setStyle("-fx-font-style: italic");
		return new TextFlow(textPoweredBy);
	}

	private TextFlow getBuildFlow(AboutDetails aboutDetails) {
		TextFlow buildFlow = new TextFlow();
		buildFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
		buildFlow.getChildren().add(getText("legyver.defaults.label.about.build.version"));
		buildFlow.getChildren().add(new Text(" "));
		buildFlow.getChildren().add(new Text(aboutDetails.getVersion()));
		buildFlow.getChildren().add(new Text(". "));
		buildFlow.getChildren().add(getText("legyver.defaults.label.about.build.date"));
		buildFlow.getChildren().add(new Text(" "));
		buildFlow.getChildren().add(new Text(aboutDetails.getBuildDate()));
		return buildFlow;
	}
}
