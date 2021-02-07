package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.options.IconOptions;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;

/**
 * Options describing an icon, the tooltip, and the action to take when clicked.
 */
public class TooltipIconOptions extends IconOptions implements IconWidgetVisitorOptions {

	private final String toolTip;
	private EventHandler<ActionEvent> onClick;

	/**
	 * Construct options that contain the icon, iconColor, toolTip, onClick-handler, iconSize and blend mode
	 * @param icon the icon to use
	 * @param iconColor the color to paint the icon
	 * @param toolTip the tooltip to display for the icon
	 * @param onClick the action to trigger when clicked
	 * @param iconSize the size of the icon
	 * @param blendMode the blend mode of the icon
	 */
	public TooltipIconOptions(String icon, String iconColor, String toolTip, EventHandler<ActionEvent> onClick, double iconSize, BlendMode blendMode) {
		super(icon, iconColor, iconSize, blendMode);
		this.toolTip = toolTip;
		this.onClick = onClick;
	}

	/**
	 * Construct options that contain the icon, iconColor, toolTip, onClick-handler and iconSize
	 * @param icon the icon to use
	 * @param iconColor the color to paint the icon
	 * @param toolTip the tooltip to display for the icon
	 * @param onClick the action to trigger when clicked
	 * @param iconSize the size of the icon
	 */
	public TooltipIconOptions(String icon, String iconColor, String toolTip, EventHandler<ActionEvent> onClick, double iconSize) {
		this(icon, iconColor, toolTip, onClick, iconSize, null);
	}

	/**
	 * Set the on-click handler for the icon
	 * @param onClick the handler
	 */
	public void setOnClick(EventHandler<ActionEvent> onClick) {
		this.onClick = onClick;
	}

	/**
	 * Get the tooltip
	 * @return the tooltip
	 */
	public String getToolTip() {
		return toolTip;
	}

	/**
	 * Get the event handler for the Icon
	 * @return the event handler
	 */
	public EventHandler<ActionEvent> getOnClick() {
		return onClick;
	}

	@Override
	public void accept(IconOptionVisitor visitor) throws CoreException {
		visitor.visit(this);
	}
}
