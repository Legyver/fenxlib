package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.validation.base.ValidatorBase;
import com.legyver.fenxlib.factory.options.blade.instantiator.TextFieldInstantiator;

public class NameFieldTextOption extends AbstractNameFieldOption {

	public NameFieldTextOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan, TextFieldInstantiator.PLAIN_TEXT);
	}

	public NameFieldTextOption(String label, boolean readOnly, ValidatorBase... validators) {
		super(label, readOnly, TextFieldInstantiator.PLAIN_TEXT, validators);
	}

}
