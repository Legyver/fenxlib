package com.legyver.fenxlib.factory.options.blade;

import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.collections.ObservableList;

public class NameListClickOption extends AbstractBladeGridLayout implements BladeOption<ObservableList> {
	private final String label;
	private final boolean readOnly;

	public NameListClickOption(String label, int labelSpan, boolean readOnly) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
		this.readOnly = readOnly;
	}

	public NameListClickOption(String label, boolean readOnly) {
		this(label, 1, readOnly);
	}

	public String getLabel() {
		return label;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) {
		visitor.visit(this, row);
	}
}
