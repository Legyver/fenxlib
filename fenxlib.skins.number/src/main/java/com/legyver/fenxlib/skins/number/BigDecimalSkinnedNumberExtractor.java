package com.legyver.fenxlib.skins.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * A convenience utility for extracting the BigDecimal value from a text field in a consistent way.
 */
public class BigDecimalSkinnedNumberExtractor {

	private final DecimalFormat formatter;
	private final String symbol;
	private final boolean leadingSymbol;

	/**
	 * Construct a number extractor to extract the BigDecimal value from a text field
	 * @param formatter the decimal format
	 * @param symbol the symbol (leading or trailing) the number
	 * @param leadingSymbol true if the symbol comes before the number and false otherwise
	 */
	protected BigDecimalSkinnedNumberExtractor(DecimalFormat formatter, String symbol, boolean leadingSymbol) {
		this.formatter = formatter;
		this.symbol = symbol;
		this.leadingSymbol = leadingSymbol;
	}

	/**
	 * Get the BigDecimal value held by a SkinnableNumberField.
	 * If there is no value set, returns 0
	 * @param skinnable the field to extract the value from
	 * @return the value
	 * @throws ParseException if the value is unable to be parsed as a BigDecimal
	 */
	protected BigDecimal getNumber(SkinnableNumberField skinnable) throws ParseException {
		BigDecimal value = skinnable.getValue();
		BigDecimal newValue;
		String text = skinnable.getText() == null ? "" : skinnable.getText().trim();
		if ("".equals(text) || symbol.equals(text)) {
			newValue = value == null ? null : BigDecimal.ZERO;
		} else {
			if (leadingSymbol && !text.startsWith(symbol)) {
				text = symbol + text;
			} else if (!leadingSymbol && !text.endsWith(symbol)) {
				text = text + symbol;
			}
			Number n = formatter.parse(text);
			newValue = n instanceof BigDecimal ? (BigDecimal) n : new BigDecimal(n.doubleValue());
		}
		return newValue;
	}

	/**
	 * Get the decimal format
	 * @return the decimal format to use
	 */
	public DecimalFormat getFormatter() {
		return formatter;
	}

}
