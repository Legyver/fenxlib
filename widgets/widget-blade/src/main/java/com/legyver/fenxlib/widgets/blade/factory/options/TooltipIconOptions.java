package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.options.IconOptions;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;

public class TooltipIconOptions extends IconOptions implements IconWidgetVisitorOptions {

	private final String toolTip;
	private EventHandler<ActionEvent> onClick;

	public TooltipIconOptions(String icon, String iconColor, String toolTip, EventHandler<ActionEvent> onClick, double iconSize, BlendMode blendMode) {
		super(icon, iconColor, iconSize, blendMode);
		this.toolTip = toolTip;
		this.onClick = onClick;
	}

	public TooltipIconOptions(String icon, String iconColor, String toolTip, EventHandler<ActionEvent> onClick, double iconSize) {
		this(icon, iconColor, toolTip, onClick, iconSize, null);
	}

	public void setOnClick(EventHandler<ActionEvent> onClick) {
		this.onClick = onClick;
	}

	public String getToolTip() {
		return toolTip;
	}

	public EventHandler<ActionEvent> getOnClick() {
		return onClick;
	}

	@Override
	public void accept(IconOptionVisitor visitor) throws CoreException {
		visitor.visit(this);
	}
}
