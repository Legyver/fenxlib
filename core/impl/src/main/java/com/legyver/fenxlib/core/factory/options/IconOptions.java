package com.legyver.fenxlib.core.factory.options;

import javafx.scene.effect.BlendMode;

public abstract class IconOptions {
	private final String icon;
	private final String iconColor;
	private final double iconSize;
	private final BlendMode blendMode;

	public IconOptions(String icon, String iconColor, double iconSize, BlendMode blendMode) {
		this.icon = icon;
		this.iconColor = iconColor;
		this.iconSize = iconSize;
		this.blendMode = blendMode;
	}

	public BlendMode getBlendMode() {
		return blendMode;
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
