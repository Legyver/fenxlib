package com.legyver.fenxlib.skin;

import com.jfoenix.skins.JFXTextFieldSkin;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;

public abstract class AbstractNumberSkin extends JFXTextFieldSkin<SkinnableNumberField> {

	private final SkinnableNumberField skinnable;
	private final BigDecimalSkinnedNumberExtractor extractor;
	private final DecimalFormat formatter;
	private final String symbol;
	private final boolean leadingSymbol;

	public AbstractNumberSkin(final SkinnableNumberField skinnable, boolean leadingSymbol) {
		super(skinnable);
		this.skinnable = skinnable;
		this.leadingSymbol = leadingSymbol;
		skinnable.setReplaceText(this::accept);
		// Align the text to the right
		skinnable.setAlignment(Pos.BASELINE_RIGHT);

		// Whenever the text of the textField changes, we may need to
		// update the value.
		skinnable.textProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				updateValue();
			}
		});

		// Make sure the text is updated to the initial state of the MoneyField value
		formatter = getFormatter(Locale.getDefault());
		symbol = getSymbol(formatter);
		extractor = new BigDecimalSkinnedNumberExtractor(formatter, symbol, leadingSymbol);
		updateText();
	}

	protected void updateText() {
		BigDecimal value = skinnable.getValue();
		skinnable.setText(value == null ? "" : formatter.format(value));
	}

	protected void updateValue() {
		boolean updateText = !"".equals(skinnable.getText());
		try {
			BigDecimal value = skinnable.getValue();
			BigDecimal newValue = extractor.getNumber(skinnable);

			if (value != newValue) {
				if (value == null || newValue == null || !value.equals(newValue)) {
					skinnable.setValue(newValue);
					updateText = true;
				}
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} finally {
			if (updateText) {
				// Weird bug where updating text while processing causes the caret
				// to end up in the wrong place.
				Platform.runLater(getRunLater(skinnable, formatter));
			}
		}
	}

	protected boolean accept(String text) {
		text = text.trim();
		if (text.length() == 0) {
			return true;
		}

		if (leadingSymbol && text.startsWith(symbol)) {
			text = text.substring(symbol.length()).trim();
		} else if (!leadingSymbol && text.endsWith(symbol)) {
			text = text.substring(0, text.length() - 1).trim();
		}

		// There must be no illegal characters.
		// If there is a thousands separator, then it must be used correctly
		// There may be only a single decimal separator
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		final char thousandsSeparator = symbols.getGroupingSeparator();
		final char decimalSeparator = symbols.getMonetaryDecimalSeparator();
		boolean thousandsSeparatorInUse = false;
		int decimalSeparatorIndex = -1;
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (ch == thousandsSeparator) {
				thousandsSeparatorInUse = true;
			} else if (decimalSeparatorIndex == -1 && ch == decimalSeparator) {
				decimalSeparatorIndex = i;
			} else if (!Character.isDigit(ch)) {
				return false;
			}
		}

		if (!validateTrailing(formatter, text, decimalSeparatorIndex)) {
			return false;
		}

		// If there are thousands separators, then make sure they are correctly spaced
		if (thousandsSeparatorInUse) {
			// 43,234,234.00
			if (text.charAt(0) == thousandsSeparator) {
				return false;
			}
			int count = 1;
			for (int i = (decimalSeparatorIndex == -1 ? text.length() - 1 : decimalSeparatorIndex - 1); i > 0; i--, count++) {
				if (count % 4 == 0) {
					if (text.charAt(i) != ',') {
						return false;
					}
				} else {
					if (text.charAt(i) == ',') {
						return false;
					}
				}
			}
		}
		return true;
	}

	protected abstract DecimalFormat getFormatter(Locale locale);

	protected abstract Runnable getRunLater(SkinnableNumberField skinnable, DecimalFormat formatter);

	protected abstract String getSymbol(DecimalFormat formatter);

	protected abstract boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex);
}
