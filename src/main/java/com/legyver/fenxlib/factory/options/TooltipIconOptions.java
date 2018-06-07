package com.legyver.fenxlib.factory.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TooltipIconOptions extends IconOptions {

	private final String toolTip;
	private EventHandler<ActionEvent> onClick;

	public TooltipIconOptions(String icon, String iconColor, String toolTip, EventHandler<ActionEvent> onClick, double iconSize) {
		super(icon, iconColor, iconSize);
		this.toolTip = toolTip;
		this.onClick = onClick;
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
}
