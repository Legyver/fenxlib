package com.legyver.fenxlib.factory.options;

import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.property.StringProperty;

public class NameFieldOption implements BladeOption<StringProperty> {
	private final String label;
	private final boolean readOnly;

	public NameFieldOption(String label, boolean readOnly) {
		this.label = label;
		this.readOnly = readOnly;
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) {
		visitor.visit(this, row);
	}

	public String getLabel() {
		return label;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

}
