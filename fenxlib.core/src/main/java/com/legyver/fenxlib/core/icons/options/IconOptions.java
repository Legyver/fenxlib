package com.legyver.fenxlib.core.icons.options;

import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

/**
 * Options for icons
 *
 */
public class IconOptions {
	/**
	 * The icon to use
	 * Example: "search-plus"
	 */
	private String icon;
	/**
	 * The family of the icon
	 * Example: "iconmoon"
	 */
	private String family;
	/**
	 * The icon color
	 * Example: "#4287f5"
	 */
	private String iconColorString;
	/**
	 * The derived icon color
	 */
	private Color color;
	/**
	 * The icon size
	 * Example: 12
	 */
	private Double iconSize;
	/**
	 * Blend mode to specify
	 */
	private BlendMode blendMode;

	/**
	 * Construct Icon Options with the given values
	 * @param icon the icon to use
	 * @param family the family of the icon
	 * @param iconColorString the color of the icon
	 * @param color the color of the icon
	 * @param iconSize the size of the icon
	 * @param blendMode the blend mode
	 */
	public IconOptions(String icon, String family, String iconColorString, Color color, double iconSize, BlendMode blendMode) {
		this.icon = icon;
		this.family = family;
		this.iconColorString = iconColorString;
		this.color = color;
		this.iconSize = iconSize;
		this.blendMode = blendMode;
	}

	/**
	 * Construct used by builder
	 */
	protected IconOptions() {
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
	public String getIconColorString() {
		return iconColorString;
	}

	/**
	 * Get the icon color
	 * @return the icon color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Get the icon size
	 * @return the icon size
	 */
	public Double getIconSize() {
		return iconSize;
	}

	/**
	 * Builder for providing {@link IconOptions}
	 */
	public static class Builder<B extends Builder, I extends IconOptions> {
		private String icon;
		private String family;
		private String iconColorString;
		private Color iconColor;
		private Double iconSize = 10.00;
		private BlendMode blendMode;

		/**
		 * Build {@link IconOptions}
		 * @return icon options
		 */
		public final I build() {
			I options = buildInternal();
			setValuesIfNotSet(options);
			return options;
		}

		private void setValuesIfNotSet(IconOptions options) {
			if (options.icon == null) {
				options.icon = icon;
			}
			if (options.family == null) {
				options.family = family;
			}
			if (options.iconColorString == null) {
				options.iconColorString = iconColorString;
			}
			if (options.color == null) {
				options.color = iconColor;
			}
			if (options.iconSize == null) {
				options.iconSize = iconSize;
			}
			if (options.blendMode == null) {
				options.blendMode = blendMode;
			}
		}

		/**
		 * Internal build method to allow for subclassing IconOptions
		 * @return the instantiated IconOptions
		 */
		protected I buildInternal() {
			return (I) new IconOptions();
		}

		/**
		 * The icon to use
		 * Example: "search-plus"
		 * @param icon the icon to use
		 * @return this builder
		 */
		public B icon(String icon) {
			this.icon = icon;
			return (B) this;
		}

		/**
		 * The family of the icon
		 * Example: "iconmoon"
		 * @param family the icon font family to use
		 * @return this builder
		 */
		public B family(String family) {
			this.family = family;
			return (B) this;
		}

		/**
		 * The icon color
		 * Example: "#4287f5"
		 * @param iconColorString the color of the icon
		 * @return this builder
		 */
		public B iconColorString(String iconColorString) {
			this.iconColorString = iconColorString;
			return (B) this;
		}

		/**
		 * The icon color
		 * @param iconColor the color of the icon
		 * @return this builder
		 */
		public B iconColor(Color iconColor) {
			this.iconColor = iconColor;
			return (B) this;
		}

		/**
		 * The icon size
		 * Example: 12.0
		 * @param iconSize the size of the icon
		 * @return this builder
		 */
		public B iconSize(double iconSize) {
			this.iconSize = iconSize;
			return (B) this;
		}

		/**
		 * Blend mode to specify
		 * @param blendMode the blend mode to use
		 * @return this builder
		 */
		public B blendMode(BlendMode blendMode) {
			this.blendMode = blendMode;
			return (B) this;
		}
	}
}
