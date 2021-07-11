package com.legyver.fenxlib.skins.number;

import com.jfoenix.skins.JFXTextFieldSkin;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.geometry.Pos;

/**
 * Base class for number skins.
 * {@link CurrencyFieldSkin} and {@link PercentageFieldSkin} known implementations
 */
public abstract class AbstractNumberSkin extends JFXTextFieldSkin<SkinnableNumberField> {

	private final SkinnableNumberField skinnable;
	private final BigDecimalSkinnedNumberExtractor extractor;
	private final DecimalFormat formatter;
	private final String symbol;
	private final boolean leadingSymbol;

	/**
	 * Construct a number skin to format a wrapped number field.
	 * @param skinnable the skinnable text field
	 * @param leadingSymbol true of the symbol should be inserted before the number
	 */
	public AbstractNumberSkin(final SkinnableNumberField skinnable, boolean leadingSymbol) {
		super(skinnable);
		this.skinnable = skinnable;
		this.leadingSymbol = leadingSymbol;
		skinnable.setReplaceText(this::accept);
		// Align the text to the right
		skinnable.setAlignment(Pos.BASELINE_RIGHT);

		// Whenever the text of the textField changes, we may need to
		// update the value.
		skinnable.textProperty().addListener((Observable observable) -> {
			updateValue();
		});

		// Make sure the text is updated to the initial state of the MoneyField value
		formatter = getFormatter(Locale.getDefault());
		symbol = getSymbol(formatter);
		extractor = new BigDecimalSkinnedNumberExtractor(formatter, symbol, leadingSymbol);
		updateText();
		skinnable.setNumberSkin(this);
	}

	/**
	 * Set the BigDecimal value on the text field appropriately formatted.
	 */
	protected void updateText() {
		BigDecimal value = skinnable.getValue();
		skinnable.setText(value == null ? "" : formatter.format(value));
	}

	/**
	 * Get the text in the field and marshall it to the BigDecimal value object
	 */
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

	/**
	 * Take the text as it is typed in and format it.
	 * @param text the entered text
	 * @return true if text can be replaced, false otherwise
	 */
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

		//Decimal, Thousands separator, etc
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

	/**
	 * Get the DecimalFormatter to be used by the skin
	 * @param locale the locale for the formatter
	 * @return the formatter
	 */
	protected abstract DecimalFormat getFormatter(Locale locale);

	/**
	 * The formatting needs to be applied in a subsequent pulse to the data population.
	 * This is a hook to return the runnable to do this.
	 * @param skinnable the field where the formatting will be set
	 * @param formatter the formatter for the decimal number
	 * @return the runnable to prepend/append the symbol to the value.
	 */
	protected abstract Runnable getRunLater(SkinnableNumberField skinnable, DecimalFormat formatter);

	/**
	 * Get the expected symbol based on the formatter.
	 * ie: For currency this will be the local currency symbol, where as for percentages it will be the percent symbol
	 * @param formatter the formatter to use while extracting the symbol
	 * @return the symbol
	 */
	protected abstract String getSymbol(DecimalFormat formatter);

	/**
	 * Test for trailing symbols
	 * @param formatter the formatter to use
	 * @param text the value
	 * @param decimalSeparatorIndex the index of the decimal character
	 * @return true if there a trailing symbol is expected.
	 */
	protected abstract boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex);
}
