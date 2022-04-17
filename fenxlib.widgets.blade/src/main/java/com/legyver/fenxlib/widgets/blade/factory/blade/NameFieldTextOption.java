package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.fenxlib.core.controls.factory.NodeInstantiator;
import javafx.scene.control.TextField;

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
	 */
	public NameFieldTextOption(String label, boolean readOnly) {
		super(label, readOnly);
	}

}
