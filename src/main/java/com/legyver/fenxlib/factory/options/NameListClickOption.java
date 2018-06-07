package com.legyver.fenxlib.factory.options;

import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.collections.ObservableList;

public class NameListClickOption implements BladeOption<ObservableList> {
	private final String label;
	private final boolean readOnly;

	public NameListClickOption(String label, boolean readOnly) {
		this.label = label;
		this.readOnly = readOnly;
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
