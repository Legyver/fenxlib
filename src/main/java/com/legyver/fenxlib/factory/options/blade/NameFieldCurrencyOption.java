package com.legyver.fenxlib.factory.options.blade;

import com.legyver.fenxlib.factory.options.blade.instantiator.TextFieldInstantiator;

public class NameFieldCurrencyOption extends AbstractNameFieldOption {

	public NameFieldCurrencyOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan, TextFieldInstantiator.CURRENCY);
	}

	public NameFieldCurrencyOption(String label, boolean readOnly) {
		super(label, readOnly, TextFieldInstantiator.CURRENCY);
	}

}
