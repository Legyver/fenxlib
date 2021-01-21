package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;

/**
 * Layout option for
 *   &lt;Label&gt;&lt;DatePicker&gt;
 */
public class NameDatePickerOption extends AbstractBladeGridLayout implements BladeOption {
	private final String label;

	/**
	 * Construct the option for a date field to be included in the form-row along with a label
	 * @param label the label
	 * @param labelSpan the number of columns the label spans
	 */
	public NameDatePickerOption(String label, int labelSpan) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
	}

	/**
	 * Construct the option for a date field to be included in the form-row along with a label
	 * @param label the label
	 */
	public NameDatePickerOption(String label) {
		this(label, 1);
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	/**
	 * Get the label for the date field
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

}
