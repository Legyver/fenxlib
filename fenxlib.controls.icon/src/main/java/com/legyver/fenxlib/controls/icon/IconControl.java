package com.legyver.fenxlib.controls.icon;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;

/**
 * Control that wraps a SVG Icon
 */
public class IconControl extends Control {
	/**
	 * The icon color
	 */
	private final ObjectProperty<Paint> iconPaint = new SimpleObjectProperty<>();
	/**
	 * The library prefix to use.  ie: fa-free-regular
	 */
	private final StringProperty iconFontFamily = new SimpleStringProperty();
	/**
	 * The icon to use within the designated library
	 */
	private final StringProperty iconName = new SimpleStringProperty();
	/**
	 * The size of the icon
	 */
	private final DoubleProperty iconSize = new SimpleDoubleProperty(20L);

	/**
	 * Construct a SVG Control
	 */
	public IconControl() {
		getStyleClass().add("icon-button");
		setFocusTraversable(true);
	}

	/**
	 * Set the following as specified by the iconOptions
	 * - svgIcon
	 * - svgIconLibraryPrefix
	 * - svgIconPaint
	 * - svgIconSize
	 * @param iconOptions the icon specifications
	 */
	public void setIconOptions(IconOptions iconOptions) {
		iconName.setValue(iconOptions.getIcon());
		iconFontFamily.setValue(iconOptions.getFamily());
		iconPaint.setValue(Paint.valueOf(iconOptions.getIconColor()));
		iconSize.setValue(iconOptions.getIconSize());
	}

	/**
	 * Get the icon library prefix
	 * @return the prefix for the icon library
	 */
	public String getIconFontFamily() {
		return iconFontFamily.get();
	}

	/**
	 * Get the icon library prefix property for binding
	 * @return the property for the icon library prefix
	 */
	public StringProperty iconFontFamilyProperty() {
		return iconFontFamily;
	}

	/**
	 * Set the icon library prefix
	 * @param iconFontFamily the prefix for the icon library
	 */
	public void setIconFontFamily(String iconFontFamily) {
		this.iconFontFamily.set(iconFontFamily);
	}

	/**
	 * Get the icon identifier
	 * @return the identifier for the icon
	 */
	public String getIconName() {
		return iconName.get();
	}

	/**
	 * Get the icon identifier property
	 * @return the property for the icon identifier
	 */
	public StringProperty iconNameProperty() {
		return iconName;
	}

	/**
	 * Set the icon identifier
	 * @param iconName the svg icon identifier
	 */
	public void setIconName(String iconName) {
		this.iconName.set(iconName);
	}

	/**
	 * Get the icon size
	 * @return the size for the icon
	 */
	public double getIconSize() {
		return iconSize.get();
	}

	/**
	 * Get the icon size property
	 * @return the property for the icon size
	 */
	public DoubleProperty iconSizeProperty() {
		return iconSize;
	}

	/**
	 * Set the icon size
	 * @param iconSize the svg icon size
	 */
	public void setIconSize(double iconSize) {
		this.iconSize.set(iconSize);
	}

	/**
	 * Get the icon color
	 * @return the color for the icon
	 */
	public Paint getIconPaint() {
		return iconPaint.get();
	}

	/**
	 * Get the icon color property
	 * @return the property for the icon color
	 */
	public ObjectProperty<Paint> iconPaintProperty() {
		return iconPaint;
	}

	/**
	 * Set the icon color
	 * @param iconPaint the svg icon color
	 */
	public void setIconPaint(Paint iconPaint) {
		this.iconPaint.set(iconPaint);
	}

	@Override
	public String getUserAgentStylesheet() {
		return IconControl.class.getResource("icon-button.css").toExternalForm();
	}

	@Override
	public Skin<?> createDefaultSkin() {
		return new IconControlSkin(this);
	}

}
