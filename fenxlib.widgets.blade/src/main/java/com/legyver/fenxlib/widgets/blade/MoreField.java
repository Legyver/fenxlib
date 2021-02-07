package com.legyver.fenxlib.widgets.blade;

import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * A label that when clicked shows/hides an additional form section.
 */
public class MoreField extends Label {
	/**
	 * Property controlling the showing/hiding of the "more" section of the form
	 */
	private final BooleanProperty expanded = new SimpleBooleanProperty();

	/**
	 * Construct a toggle label that hides/shows a form section
	 * @param showText the text to display when the section is hidden
	 * @param hideText the text to display when the section is displayed
	 */
	public MoreField(String showText, String hideText) {
		expanded.set(false);
		super.textProperty().bind(new When(expanded).then(hideText).otherwise(showText));
		setOnMouseClicked((MouseEvent event) -> {
			setExpanded(!isExpanded());
		});
	}

	/**
	 * Set the expanded property
	 * @param expanded the value to set it to
	 */
	public void setExpanded(boolean expanded) {
		this.expanded.set(expanded);
	}

	/**
	 * Indicate if the "more" section is currently visible
	 * @return expanded flag
	 */
	public boolean isExpanded() {
		return expanded.get();
	}

	/**
	 * Get the property monitoring if the "more" section is expanded
	 * @return the expanded property
	 */
	public BooleanProperty expandedProperty() {
		return expanded;
	}
}
