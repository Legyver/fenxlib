package com.legyver.fenxlib.widgets.license;

import com.legyver.fenxlib.core.web.DesktopWeblink;
import javafx.geometry.HPos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Skin for an OpenSourceReferenceList
 */
public class OpenSourceReferenceListSkin extends SkinBase<OpenSourceReferenceList> {
	private final GridPane gridPane;

	/**
	 * Construct a skin for an OpenSourceReferenceList
	 * @param openSourceReferenceList the open source reference list
	 */
	public OpenSourceReferenceListSkin(OpenSourceReferenceList openSourceReferenceList) {
		super(openSourceReferenceList);
		gridPane = new GridPane();
		StackPane contentStack = new StackPane(gridPane);
		contentStack.getStyleClass().add("content");
		List<OpenSourceReferenceList.Item> items = openSourceReferenceList.getItems();

		for (int i = 0; i < items.size(); i++) {
			OpenSourceReferenceList.Item item = items.get(i);

			Text artifact = new Text(item.getArtifact());
			gridPane.add(artifact, 0, i);
			artifact.getStyleClass().add("item");

			if (item.getLicenseLinks().isEmpty()) {
				gridPane.add(new Text(item.getLicenseNames().get(0)), 1, i);//there's a license but no link
			} else {
				String licenseName = "linked";
				HBox hBox = new HBox();
				hBox.getStyleClass().add("item-link");
				for (int j = 0; j < item.getLicenseLinks().size(); j++) {
					String licenseLinkPart = item.getLicenseLinks().get(j);
					if (j < item.getLicenseNames().size()) {
						licenseName = item.getLicenseNames().get(j);
					}
					Hyperlink link = new DesktopWeblink(licenseName, licenseLinkPart);
					if (hBox.getChildren().size() > 0) {
						hBox.getChildren().add(new Label("/"));
					}
					hBox.getChildren().add(link);
				}
				gridPane.add(hBox, 1, i);
			}
		}
		gridPane.getColumnConstraints().addAll(
				new ColumnConstraints(120, 400, 800, Priority.ALWAYS, HPos.LEFT, false),
				new ColumnConstraints(120, 200, 800, Priority.SOMETIMES, HPos.LEFT, true)
		);

		getChildren().addAll(contentStack);
	}
}
