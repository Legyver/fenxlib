package com.legyver.fenxlib.core.impl.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import com.legyver.fenxlib.core.api.factory.NodeFactory;

public class ButtonTooltipDecorator implements NodeFactory<Button> {

	private final NodeFactory<Button> factory;
	private final String toolTip;

	public ButtonTooltipDecorator(String toolTip, NodeFactory<Button> factory) {
		this.factory = factory;
		this.toolTip = toolTip;
	}

	@Override
	public Button makeNode(LocationContext locationContext) throws CoreException {
		Button button = factory.makeNode(locationContext);
		Tooltip.install(button, new Tooltip(toolTip));
		return button;
	}

}
