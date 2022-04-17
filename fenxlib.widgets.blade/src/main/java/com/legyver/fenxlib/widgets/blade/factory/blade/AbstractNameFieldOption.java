package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.property.StringProperty;

/**
 * Abstract super class of the NameFieldOptions
 */
public abstract class AbstractNameFieldOption extends AbstractBladeGridLayout implements BladeOption<StringProperty> {

	private final String label;
	private final boolean readOnly;

	/**
	 * Construct an option for a labeled text field.
	 * @param label the label
	 * @param readOnly read-only flag
	 * @param labelSpan number of columns the label spans
	 */
	public AbstractNameFieldOption(String label, boolean readOnly, int labelSpan) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
		this.readOnly = readOnly;
	}

	/**
	 * Construct an option for a labeled text field.  The validators are currently ignored.
	 * @param label the label
	 * @param readOnly read-only flag
	 */
	public AbstractNameFieldOption(String label, boolean readOnly) {
		this(label, readOnly, 1);
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	/**
	 * Get the label
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Indicate if the field is read-only
	 * @return true if the field is read-only, otherwise false
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

}
