package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import com.legyver.fenxlib.factory.NodeInstantiator;

public class NameFieldTextOption extends AbstractNameFieldOption {

	public NameFieldTextOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan);
	}

	public NameFieldTextOption(String label, boolean readOnly, ValidatorBase... validators) {
		super(label, readOnly, validators);
	}

	@Override
	public NodeInstantiator<JFXTextField> getInstantiator() {
		return () -> new JFXTextField();
	}

}
