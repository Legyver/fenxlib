package com.legyver.fenxlib.widget.about;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.commons.lang3.StringUtils;

public class AboutDetailsSkin extends SkinBase<AboutDetails> {
	private final StackPane buildStack;
	private final StackPane openSourceTaglineStack;

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
		Text textPoweredBy = new Text(poweredByClause);
		textPoweredBy.setId("powered-by");
//		textPoweredBy.setStyle("-fx-font-style: italic");
		return new TextFlow(textPoweredBy);
	}

	private TextFlow getGenericFlow(String text) {
		TextFlow flow = new TextFlow(new Text(text));
		flow.setPrefWidth(Region.USE_COMPUTED_SIZE);
		return flow;
	}

	private StackPane style(String cssClass, TextFlow text) {
		StackPane styledPane = new StackPane(text);
		styledPane.getStyleClass().add(cssClass);
		return styledPane;
	}

	private TextFlow getBuildFlow(AboutDetails aboutDetails) {
		TextFlow buildFlow = new TextFlow();
		buildFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
		buildFlow.getChildren().add(new Text("Build: "));
		buildFlow.getChildren().add(new Text(aboutDetails.getVersion()));
		buildFlow.getChildren().add(new Text(". Built on "));
		buildFlow.getChildren().add(new Text(aboutDetails.getBuildDate()));
		return buildFlow;
	}
}
