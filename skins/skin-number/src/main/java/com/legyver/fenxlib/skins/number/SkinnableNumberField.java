package com.legyver.fenxlib.skins.number;

import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.util.function.Predicate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class SkinnableNumberField extends JFXTextField {
	private final ObjectProperty<BigDecimal> value = new SimpleObjectProperty<>(this, "value");
	private AbstractNumberSkin numberSkin;
	private Predicate<String> replaceText;

	public SkinnableNumberField(String cssClass) {
		getStyleClass().setAll(cssClass);
		value.addListener((ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) -> {
			if (numberSkin != null) {
				numberSkin.updateText();
			}
		});
	}

	public final BigDecimal getValue() {
		return value.get();
	}

	public final void setValue(BigDecimal value) {
		this.value.set(value);
	}

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