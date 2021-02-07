package com.legyver.fenxlib.core.impl.factory.decorator;

import com.jfoenix.svg.SVGGlyph;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.factory.SvgIconFactory;

public class ButtonIconDecorator implements NodeFactory<Button> {
	private final SvgIconFactory factory;
	private final EventHandler<ActionEvent> onClick;

	public ButtonIconDecorator(EventHandler<ActionEvent> onClick, SvgIconFactory factory) {
		this.onClick = onClick;
		this.factory = factory;
	}

	@Override
	public Button makeNode(LocationContext locationContext) throws CoreException {
		SVGGlyph glyph = factory.makeNode(locationContext);
		Button button = new Button(null, glyph);//no text
		button.maxHeightProperty().bind(glyph.heightProperty().multiply(1.2));
		button.maxWidthProperty().bind(glyph.widthProperty().multiply(1.2));
		button.getStyleClass().clear();
		button.getStyleClass().add("hidden-button");
		button.setOnAction(onClick);
		return button;
	}

}
