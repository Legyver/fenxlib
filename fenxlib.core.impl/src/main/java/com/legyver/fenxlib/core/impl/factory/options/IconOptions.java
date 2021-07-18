package com.legyver.fenxlib.core.impl.factory.options;

import javafx.scene.effect.BlendMode;

/**
 * Options for icons
 *
 */
public class IconOptions {
	/**
	 * The icon to use
	 * Example: "search-plus"
	 */
	private final String icon;
	/**
	 * The icon color
	 * Example: "#4287f5"
	 */
	private final String iconColor;
	/**
	 * The icon size
	 * Example: 12
	 */
	private final double iconSize;
	/**
	 * Blend mode to specify
	 */
	private final BlendMode blendMode;

	/**
	 * Construct Icon Options with the given values
	 * @param icon the icon to use
	 * @param iconColor the color of the icon
	 * @param iconSize the size of the icon
	 * @param blendMode the blend mode
	 */
	public IconOptions(String icon, String iconColor, double iconSize, BlendMode blendMode) {
		this.icon = icon;
		this.iconColor = iconColor;
		this.iconSize = iconSize;
		this.blendMode = blendMode;
	}

	/**
	 * Get the blend mode
	 * @return the blend mode
	 */
	public BlendMode getBlendMode() {
		return blendMode;
	}

	/**
	 * Get the icon
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Get the icon color
	 * @return the icon color
	 */
	public String getIconColor() {
		return iconColor;
	}

	/**
	 * Get the icon size
	 * @return the icon size
	 */
	public double getIconSize() {
		return iconSize;
	}
}
