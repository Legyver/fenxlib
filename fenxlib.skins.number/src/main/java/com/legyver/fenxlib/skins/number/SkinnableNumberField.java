package com.legyver.fenxlib.skins.number;

import java.math.BigDecimal;
import java.util.function.Predicate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * A number field for BigDecimal values to allow for formatting of numbers
 */
public class SkinnableNumberField extends TextField {
	/**
	 * The BigDecimal value of the field
	 */
	private final ObjectProperty<BigDecimal> value = new SimpleObjectProperty<>(this, "value");
	private AbstractNumberSkin numberSkin;
	private Predicate<String> replaceText;

	/**
	 * Construct a SkinnableNumberField with a specified css class
	 * @param cssClass the css class
	 */
	public SkinnableNumberField(String cssClass) {
		getStyleClass().setAll(cssClass);
		value.addListener((ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) -> {
			if (numberSkin != null) {
				numberSkin.updateText();
			}
		});
	}

	/**
	 * Get the value
	 * @return the value
	 */
	public final BigDecimal getValue() {
		return value.get();
	}

	/**
	 * Set the value
	 * @param value the value
	 */
	public final void setValue(BigDecimal value) {
		this.value.set(value);
	}

	/**
	 * Get the property the value is stored in
	 * @return the property
	 */
	public final ObjectProperty<BigDecimal> valueProperty() {
		return value;
	}

	@Override
	public void replaceText(int start, int end, String text) {
		String t = getText() == null ? "" : getText();
		t = t.substring(0, start) + text + t.substring(end);
		if (replaceText.test(t)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		String t = getText() == null ? "" : getText();
		int start = Math.min(getAnchor(), getCaretPosition());
		int end = Math.max(getAnchor(), getCaretPosition());
		t = t.substring(0, start) + text + t.substring(end);
		if (replaceText.test(t)) {
			super.replaceSelection(text);
		}
	}

	void setReplaceText(Predicate<String> replaceText) {
		this.replaceText = replaceText;
	}

	void setNumberSkin(AbstractNumberSkin numberSkin) {
		this.numberSkin = numberSkin;
	}
}
