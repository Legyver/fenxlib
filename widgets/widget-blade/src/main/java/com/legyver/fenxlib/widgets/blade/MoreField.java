package com.legyver.fenxlib.widgets.blade;


import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MoreField extends Label {
	private final BooleanProperty expanded = new SimpleBooleanProperty();
	private final String showText;
	private final String hideText;
	
	public MoreField(String showText, String hideText) {
		this.showText = showText;
		this.hideText = hideText;
		expanded.set(false);
		super.textProperty().bind(new When(expanded).then(hideText).otherwise(showText));
		setOnMouseClicked((MouseEvent event) -> {
			setExpanded(!isExpanded());
		});
	}
	
	public void setExpanded(boolean expanded) {
		this.expanded.set(expanded);
	}
	
	public boolean isExpanded() {
		return expanded.get();
	}
	
	public BooleanProperty expandedProperty() {
		return expanded;
	}
}
