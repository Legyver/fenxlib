package com.legyver.fenxlib.widgets.blade.factory.blade;

/**
 * Layout option for
 *   &lt;Label&gt;&lt;PercentageField&gt;
 */
public class NameFieldPercentageOption extends AbstractNameFieldOption {

	/**
	 * Construct an option for a text field skinned to render with a percent sign to be included in the form
	 * @param label the label for the text field
	 * @param readOnly if the text field is read-only
	 * @param labelSpan how many columns the label spans
	 */
	public NameFieldPercentageOption(String label, boolean readOnly,int labelSpan) {
		super(label, readOnly, labelSpan);
	}

}
