package com.legyver.fenxlib.skins.number;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Skin to auto-append/prepend a currency symbol to a number.
 */
public class CurrencyFieldSkin extends AbstractNumberSkin {

	/**
	 * Construct a skin for a number field that prepends/appends a currency symbol to the number
	 * @param skinnable the number field to skin
	 */
	public CurrencyFieldSkin(final SkinnableNumberField skinnable) {
		super(skinnable, true);
	}

	@Override
	protected DecimalFormat getFormatter(Locale locale) {
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
		formatter.setParseBigDecimal(true);
		return formatter;
	}

	@Override
	protected String getSymbol(DecimalFormat formatter) {
		Currency currency = formatter.getCurrency();
		return currency.getSymbol();
	}

	@Override
	protected Runnable getRunLater(SkinnableNumberField skinnable, DecimalFormat formatter) {
		return () -> {
			int caretPosition = skinnable.getCaretPosition();
			Currency currency = formatter.getCurrency();
			skinnable.insertText(0, currency.getSymbol());
			skinnable.positionCaret(caretPosition + currency.getSymbol().length());
		};
	}

	@Override
	protected boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex) {
		if (decimalSeparatorIndex != -1) {
			int trailingLength = text.substring(decimalSeparatorIndex + 1).length();
			if (trailingLength > formatter.getCurrency().getDefaultFractionDigits()) {
				return false;
			}
		}
		return true;
	}

}
