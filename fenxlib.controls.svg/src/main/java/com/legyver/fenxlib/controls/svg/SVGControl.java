package com.legyver.fenxlib.controls.svg;

import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;

public class SVGControl extends Control {
	private final ObjectProperty<Paint> svgIconPaint = new SimpleObjectProperty<>();
	private final StringProperty svgIconLibraryPrefix = new SimpleStringProperty();
	private final StringProperty svgIcon = new SimpleStringProperty();
	private final LongProperty svgIconSize = new SimpleLongProperty(20L);

	public SVGControl() {
		getStyleClass().add("svg-button");
		setFocusTraversable(true);
	}

	public String getSvgIconLibraryPrefix() {
		return svgIconLibraryPrefix.get();
	}

	public StringProperty svgIconLibraryPrefixProperty() {
		return svgIconLibraryPrefix;
	}

	public void setSvgIconLibraryPrefix(String svgIconLibraryPrefix) {
		this.svgIconLibraryPrefix.set(svgIconLibraryPrefix);
	}

	public String getSvgIcon() {
		return svgIcon.get();
	}

	public StringProperty svgIconProperty() {
		return svgIcon;
	}

	public void setSvgIcon(String svgIcon) {
		this.svgIcon.set(svgIcon);
	}

	public long getSvgIconSize() {
		return svgIconSize.get();
	}

	public LongProperty svgIconSizeProperty() {
		return svgIconSize;
	}

	public void setSvgIconSize(long svgIconSize) {
		this.svgIconSize.set(svgIconSize);
	}

	public Paint getSvgIconPaint() {
		return svgIconPaint.get();
	}

	public ObjectProperty<Paint> svgIconPaintProperty() {
		return svgIconPaint;
	}

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
