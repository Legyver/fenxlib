package com.legyver.fenxlib.core.controls.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.IconFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.event.correlation.CorrelatingEventHandlerFactory;
import com.legyver.fenxlib.core.icons.IconPane;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Decorate an icon with a clickable button
 */
public class ButtonIconDecorator implements StyleableFactory<Button> {
	private final IconFactory iconFactory;
	private final EventHandler<ActionEvent> onClick;

	/**
	 * Construct a factory to decorate the output of the icon factory with a button
	 * @param onClick what to do when the button is clicked
	 * @param iconFactory factory creating the icon
	 */
	public ButtonIconDecorator(EventHandler<ActionEvent> onClick, IconFactory iconFactory) {
		this.onClick = CorrelatingEventHandlerFactory.wrapIfNecessary(onClick);
		this.iconFactory = iconFactory;
	}

	@Override
	public Button makeNode(LocationContext locationContext) throws CoreException {
		Text icon = iconFactory.makeNode(locationContext);
		Button button = new Button(null, icon);//no text
//		button.maxHeightProperty().bind(iconPane.heightProperty().multiply(1.2));
//		button.maxWidthProperty().bind(iconPane.widthProperty().multiply(1.2));
		button.getStyleClass().clear();
		button.getStyleClass().add("hidden-button");
		button.setOnAction(onClick);
		return button;
	}

}
