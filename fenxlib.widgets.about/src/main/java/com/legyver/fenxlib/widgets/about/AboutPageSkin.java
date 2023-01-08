package com.legyver.fenxlib.widgets.about;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;

/**
 * Skin for an AboutPage
 */
public class AboutPageSkin extends SkinBase<AboutPage> {
	private final AboutDetails aboutDetails;
	private final OpenSourceAcknowledgements openSourceAcknowledgements;
	private final CopyrightNotice copyrightNotice;

	private final VBox layout;

	/**
	 * Construct a skin for the about page
	 * @param aboutPage the about page to skin
	 */
	public AboutPageSkin(AboutPage aboutPage) {
		super(aboutPage);

		aboutDetails = new AboutDetails(aboutPage.getVersion(), aboutPage.getBuildDate(), aboutPage.getIntro(), aboutPage.getGist(), aboutPage.getAdditionalInfo());
		openSourceAcknowledgements = new OpenSourceAcknowledgements(aboutPage.getOpenSourceTagLine(), aboutPage.getLicenseProperties());
		copyrightNotice = new CopyrightNotice(aboutPage.getCopyright());

		layout = new VBox(aboutDetails, openSourceAcknowledgements, copyrightNotice);
		getChildren().setAll(layout);
	}

}
