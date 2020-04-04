package com.legyver.fenxlib.factory.options.blade;

import com.legyver.fenxlib.factory.options.blade.instantiator.TextFieldInstantiator;

public class NameFieldPercentageOption extends AbstractNameFieldOption {

	public NameFieldPercentageOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan, TextFieldInstantiator.PERCENT);
	}

}
