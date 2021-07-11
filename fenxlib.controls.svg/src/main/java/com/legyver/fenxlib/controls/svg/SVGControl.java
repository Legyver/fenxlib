package com.legyver.fenxlib.controls.svg;

import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;

/**
 * Control that wraps a SVG Icon
 */
public class SVGControl extends Control {
	/**
	 * The icon color
	 */
	private final ObjectProperty<Paint> svgIconPaint = new SimpleObjectProperty<>();
	/**
	 * The library prefix to use.  ie: fa-free-regular
	 */
	private final StringProperty svgIconLibraryPrefix = new SimpleStringProperty();
	/**
	 * The icon to use within the designated library
	 */
	private final StringProperty svgIcon = new SimpleStringProperty();
	/**
	 * The size of the icon
	 */
	private final LongProperty svgIconSize = new SimpleLongProperty(20L);

	/**
	 * Construct a SVG Control
	 */
	public SVGControl() {
		getStyleClass().add("svg-button");
		setFocusTraversable(true);
	}

	/**
	 * Get the icon library prefix
	 * @return the prefix for the icon library
	 */
	public String getSvgIconLibraryPrefix() {
		return svgIconLibraryPrefix.get();
	}

	/**
	 * Get the icon library prefix property for binding
	 * @return the property for the icon library prefix
	 */
	public StringProperty svgIconLibraryPrefixProperty() {
		return svgIconLibraryPrefix;
	}

	/**
	 * Set the icon library prefix
	 * @param svgIconLibraryPrefix the prefix for the icon library
	 */
	public void setSvgIconLibraryPrefix(String svgIconLibraryPrefix) {
		this.svgIconLibraryPrefix.set(svgIconLibraryPrefix);
	}

	/**
	 * Get the icon identifier
	 * @return the identifier for the icon
	 */
	public String getSvgIcon() {
		return svgIcon.get();
	}

	/**
	 * Get the icon identifier property
	 * @return the property for the icon identifier
	 */
	public StringProperty svgIconProperty() {
		return svgIcon;
	}

	/**
	 * Set the icon identifier
	 * @param svgIcon the svg icon identifier
	 */
	public void setSvgIcon(String svgIcon) {
		this.svgIcon.set(svgIcon);
	}

	/**
	 * Get the icon size
	 * @return the size for the icon
	 */
	public long getSvgIconSize() {
		return svgIconSize.get();
	}

	/**
	 * Get the icon size property
	 * @return the property for the icon size
	 */
	public LongProperty svgIconSizeProperty() {
		return svgIconSize;
	}

	/**
	 * Set the icon size
	 * @param svgIconSize the svg icon size
	 */
	public void setSvgIconSize(long svgIconSize) {
		this.svgIconSize.set(svgIconSize);
	}

	/**
	 * Get the icon color
	 * @return the color for the icon
	 */
	public Paint getSvgIconPaint() {
		return svgIconPaint.get();
	}

	/**
	 * Get the icon color property
	 * @return the property for the icon color
	 */
	public ObjectProperty<Paint> svgIconPaintProperty() {
		return svgIconPaint;
	}

	/**
	 * Set the icon color
	 * @param svgIconPaint the svg icon color
	 */
	public void setSvgIconPaint(Paint svgIconPaint) {
		this.svgIconPaint.set(svgIconPaint);
	}

	@Override
	public String getUserAgentStylesheet() {
		return SVGControl.class.getResource("svg-button.css").toExternalForm();
	}

	@Override
	public Skin<?> createDefaultSkin() {
		return new SVGControlSkin(this);
	}

}
