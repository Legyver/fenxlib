package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;

public class NameDatePickerOption extends AbstractBladeGridLayout implements BladeOption {
	private final String label;

	public NameDatePickerOption(String label, int labelSpan) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
	}

	public NameDatePickerOption(String label) {
		this(label, 1);
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	public String getLabel() {
		return label;
	}

}
