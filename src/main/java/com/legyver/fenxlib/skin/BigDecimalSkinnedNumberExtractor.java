package com.legyver.fenxlib.skin;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class BigDecimalSkinnedNumberExtractor {

	private final DecimalFormat formatter;
	private final String symbol;
	private final boolean leadingSymbol;

	protected BigDecimalSkinnedNumberExtractor(DecimalFormat formatter, String symbol, boolean leadingSymbol) {
		this.formatter = formatter;
		this.symbol = symbol;
		this.leadingSymbol = leadingSymbol;
	}

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

	public DecimalFormat getFormatter() {
		return formatter;
	}

}
