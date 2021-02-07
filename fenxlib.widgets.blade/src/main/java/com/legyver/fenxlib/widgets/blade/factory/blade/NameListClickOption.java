package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;
import javafx.collections.ObservableList;

/**
 * Layout option for
 *   &lt;Label&gt;&lt;SelectField&gt;
 */
public class NameListClickOption extends AbstractBladeGridLayout implements BladeOption<ObservableList> {
	private final String label;
	private final boolean readOnly;

	/**
	 * Construct the option for a select field
	 * @param label the label
	 * @param labelSpan how many columns the label spans
	 * @param readOnly if the select field will be read-only
	 */
	public NameListClickOption(String label, int labelSpan, boolean readOnly) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
		this.readOnly = readOnly;
	}

	/**
	 * Construct the option for a select field on the form.  The default label span (how many columns the label field fills) is 1.
	 * @param label the label
	 * @param readOnly if the field will be read-only
	 */
	public NameListClickOption(String label, boolean readOnly) {
		this(label, 1, readOnly);
	}

	/**
	 * Get the label
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Is the list read only
	 * @return true if the list is read-only, otherwise false
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) {
		visitor.visit(this, row);
	}
}
