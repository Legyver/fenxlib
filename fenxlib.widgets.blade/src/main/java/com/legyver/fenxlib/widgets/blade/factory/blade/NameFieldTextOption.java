package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import com.legyver.fenxlib.core.controls.factory.NodeInstantiator;

/**
 * Layout option for
 *   &lt;Label&gt;&lt;TextField&gt;
 */
public class NameFieldTextOption extends AbstractNameFieldOption {

	/**
	 * Construct an option for a text field to be included in the form
	 * @param label the label for the text field
	 * @param readOnly if the text field is read-only
	 * @param labelSpan how many columns the label spans
	 */
	public NameFieldTextOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan);
	}

	/**
	 * Construct an option for a text field to be included n the form
	 * @param label the label for the text field
	 * @param readOnly if the text field is read-only
	 * @param validators any validators for the text field
	 */
	public NameFieldTextOption(String label, boolean readOnly, ValidatorBase... validators) {
		super(label, readOnly, validators);
	}

	@Override
	public NodeInstantiator<JFXTextField> getInstantiator() {
		return () -> new JFXTextField();
	}

}
