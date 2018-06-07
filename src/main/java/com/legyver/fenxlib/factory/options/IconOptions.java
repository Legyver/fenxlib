package com.legyver.fenxlib.factory.options;

public class IconOptions {
	private final String icon;
	private final String iconColor;
	private final double iconSize;

	public IconOptions(String icon, String iconColor, double iconSize) {
		this.icon = icon;
		this.iconColor = iconColor;
		this.iconSize = iconSize;
	}

	public String getIcon() {
		return icon;
	}

	public String getIconColor() {
		return iconColor;
	}

	public double getIconSize() {
		return iconSize;
	}
}
