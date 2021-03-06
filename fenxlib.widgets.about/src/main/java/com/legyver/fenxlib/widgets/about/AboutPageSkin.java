package com.legyver.fenxlib.widgets.about;

import com.legyver.fenxlib.widgets.license.OpenSourceReferenceList;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Skin for an AboutPage
 */
public class AboutPageSkin extends SkinBase<AboutPage> {
	private final StackPane aboutDetailStack;
	private final StackPane referenceListStack;
	private final StackPane copyrightStack;

	/**
	 * Construct a skin for the about page
	 * @param aboutPage the about page to skin
	 */
	public AboutPageSkin(AboutPage aboutPage) {
		super(aboutPage);
		aboutDetailStack = new StackPane();
		aboutDetailStack.getStyleClass().add("about-detail");
		referenceListStack = new StackPane();
		referenceListStack.getStyleClass().add("reference-list");
		copyrightStack = new StackPane();
		copyrightStack.getStyleClass().add("copyright");

		VBox layout = new VBox();
		layout.getChildren().addAll(aboutDetailStack, referenceListStack, copyrightStack);

		aboutDetailStack.getChildren().add(new AboutDetails(aboutPage.getVersion(), aboutPage.getBuildDate(), aboutPage.getIntro(), aboutPage.getGist(), aboutPage.getAdditionalInfo(), aboutPage.getOpenSourceTagLine()));
		referenceListStack.getChildren().add(new OpenSourceReferenceList(aboutPage.getLicenseProperties()));
		copyrightStack.getChildren().add(new CopyrightNotice(aboutPage.getCopyright()));
		getChildren().setAll(layout);
	}

}
