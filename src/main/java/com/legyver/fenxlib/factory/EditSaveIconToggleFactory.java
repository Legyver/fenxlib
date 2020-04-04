package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.decorator.ButtonIconDecorator;
import com.legyver.fenxlib.factory.decorator.ButtonTooltipDecorator;
import com.legyver.fenxlib.factory.options.SimpleIconOptions;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * Decorate text field with an Edit/save icon. There are complications around
 * using a decorator pattern here, which is why I'm just passing in the text
 * field. Namely, sometimes these are laid out on a grid and sometimes they're
 * embedded in an HBox;
 */
public class EditSaveIconToggleFactory {

	private final String editIcon;
	private final String saveIcon;
	private final String editIconColor;
	private final String saveIconColor;

	public EditSaveIconToggleFactory(String editIcon, String saveIcon, String editIconColor, String saveIconColor) {
		this.editIcon = editIcon;
		this.saveIcon = saveIcon;
		this.editIconColor = editIconColor;
		this.saveIconColor = saveIconColor;
	}

	public StackPane makeEditToggle(TextField editableField, LocationContext locationContext) throws CoreException {
		BooleanProperty editable = new SimpleBooleanProperty();
		editableField.editableProperty().bind(editable);
		StackPane sp = new StackPane();
		ButtonTooltipDecorator edit = new ButtonTooltipDecorator("Edit", new ButtonIconDecorator(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editable.setValue(Boolean.TRUE);
			}
		}, new SvgIconFactory(new SimpleIconOptions(editIcon, editIconColor, 15))));
		ButtonTooltipDecorator save = new ButtonTooltipDecorator("Save", new ButtonIconDecorator(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editable.setValue(Boolean.FALSE);
			}
		}, new SvgIconFactory(new SimpleIconOptions(saveIcon, saveIconColor, 15))));
		Button editButton = edit.makeNode(locationContext);
		editButton.visibleProperty().bind(editable.not());
		Button saveButton = save.makeNode(locationContext);
		saveButton.visibleProperty().bind(editable);
		sp.getChildren().addAll(editButton, saveButton);
		return sp;
	}

}
