package com.legyver.fenxlib.skin;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PercentageFieldSkin extends AbstractNumberSkin {

	public PercentageFieldSkin(SkinnableNumberField skinnable) {
		super(skinnable, false);
	}

	@Override
	protected DecimalFormat getFormatter(Locale locale) {
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getPercentInstance(locale);
		formatter.setParseBigDecimal(true);
		return formatter;
	}

	@Override
	protected String getSymbol(DecimalFormat formatter) {
		return "%";
	}

	@Override
	protected Runnable getRunLater(SkinnableNumberField skinnable, DecimalFormat formatter) {
		return new Runnable() {
			@Override
			public void run() {
				int caretPosition = skinnable.getCaretPosition();
				skinnable.appendText("%");
				skinnable.positionCaret(caretPosition);
			}
		};
	}

	@Override
	protected boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex) {
		return true;
	}

}
