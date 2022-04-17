package com.legyver.fenxlib.core.icons.options;

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
	 * The family of the icon
	 * Example: "iconmoon"
	 */
	private final String family;
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
	 * @param family the family of the icon
	 * @param iconColor the color of the icon
	 * @param iconSize the size of the icon
	 * @param blendMode the blend mode
	 */
	public IconOptions(String icon, String family, String iconColor, double iconSize, BlendMode blendMode) {
		this.icon = icon;
		this.family = family;
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
	 * Get the icon family
	 * @return the font-family of the icon
	 */
	public String getFamily() {
		return family;
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

	/**
	 * Builder for providing {@link IconOptions}
	 */
	public static class Builder {
		private String icon;
		private String family;
		private String iconColor;
		private Double iconSize = 10.00;
		private BlendMode blendMode;

		/**
		 * Build {@link IconOptions}
		 * @return icon options
		 */
		public IconOptions build() {
			return new IconOptions(icon, family, iconColor, iconSize, blendMode);
		}

		/**
		 * The icon to use
		 * Example: "search-plus"
		 * @param icon the icon to use
		 * @return this builder
		 */
		public Builder icon(String icon) {
			this.icon = icon;
			return this;
		}

		/**
		 * The family of the icon
		 * Example: "iconmoon"
		 * @param family the icon font family to use
		 * @return this builder
		 */
		public Builder family(String family) {
			this.family = family;
			return this;
		}

		/**
		 * The icon color
		 * Example: "#4287f5"
		 * @param iconColor the color of the icon
		 * @return this builder
		 */
		public Builder iconColor(String iconColor) {
			this.iconColor = iconColor;
			return this;
		}

		/**
		 * The icon size
		 * Example: 12.0
		 * @param iconSize the size of the icon
		 * @return this builder
		 */
		public Builder iconSize(double iconSize) {
			this.iconSize = iconSize;
			return this;
		}

		/**
		 * Blend mode to specify
		 * @param blendMode the blend mode to use
		 * @return this builder
		 */
		public Builder blendMode(BlendMode blendMode) {
			this.blendMode = blendMode;
			return this;
		}
	}
}
