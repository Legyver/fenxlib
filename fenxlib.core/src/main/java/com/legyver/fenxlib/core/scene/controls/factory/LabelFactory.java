package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

/**
 * Factory to create a label
 */
public class LabelFactory implements StyleableFactory<Label> {
	/**
	 * Constructor parameter for the StringProperty to bind the Label to.
	 */
	public static final String BIND_TO = "bind_to";
	/**
	 * Constructor parameter for the text to set as the default for the Label.
	 */
	public static final String DEFAULT_TEXT = "default_text";

	private final StringProperty textProperty;

	/**
	 * Construct a label field with either the specified text, or bound to the specified string property
	 * @param stringProperty the string property to bind the label field to, if any
	 * @param text the default text to display in the label field
	 */
	public LabelFactory(StringProperty stringProperty, String text) {
		if (stringProperty != null) {
			textProperty = stringProperty;
		} else {
			textProperty = new SimpleStringProperty();
		}
		if (text != null) {
			textProperty.setValue(text);
		}
	}

	/**
	 * Construct a factory to create a label with given text
	 * @param text the text for the label
	 */
	public LabelFactory(String text) {
		this(null, text);
	}

	/**
	 * Construct a factory to create a label with given text
	 * @param textProperty the text property for the label
	 */
	public LabelFactory(StringProperty textProperty) {
		this(textProperty, null);
	}

	@Override
	public Label makeNode(LocationContext locationContext) {
		Label label = makeLabel();
		label.autosize();
		label.textProperty().bind(textProperty);
		ApplicationContext.getComponentRegistry().register(locationContext, label);
		return label;
	}

	/**
	 * Make the label
	 * @return the label
	 */
	protected Label makeLabel() {
		Label label = new Label();
		return label;
	}

}
