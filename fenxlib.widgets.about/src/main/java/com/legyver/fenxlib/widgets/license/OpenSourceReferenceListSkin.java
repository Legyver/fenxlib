package com.legyver.fenxlib.widgets.license;

import javafx.geometry.Insets;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Skin for an OpenSourceReferenceList
 */
public class OpenSourceReferenceListSkin extends SkinBase<OpenSourceReferenceList> {
	private final HBox hBox;

	/**
	 * Construct a skin for an OpenSourceReferenceList
	 * @param openSourceReferenceList the open source reference list
	 */
	public OpenSourceReferenceListSkin(OpenSourceReferenceList openSourceReferenceList) {
		super(openSourceReferenceList);
		hBox = new HBox();
		hBox.setPadding(new Insets(5));
		VBox list = new VBox();
		list.setPadding(new Insets(10));
		hBox.getChildren().add(list);
		HBox.setHgrow(list, Priority.ALWAYS);
		LicensePortal licensePortal = new LicensePortal();
		hBox.getChildren().add(licensePortal);

		StackPane contentStack = new StackPane(hBox);
		contentStack.getStyleClass().add("content");

		List<DependencyData> items = openSourceReferenceList.getItems();

		for (int i = 0; i < items.size(); i++) {
			DependencyData item = items.get(i);
			Text artifact = new Text(item.getName());
			list.getChildren().add(artifact);
			artifact.getStyleClass().add("item");

			DependencyView dependencyView = new DependencyView(item);
			licensePortal.addLicense(dependencyView);
			artifact.addEventFilter(MouseEvent.MOUSE_ENTERED, new LicenseClickHandler(licensePortal, i));
		}
		getChildren().addAll(contentStack);
	}
}
