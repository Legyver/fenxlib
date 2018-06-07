package com.legyver.fenxlib.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.property.StringProperty;

/**
 * Used mainly for GridPanes where there are multiple rows of
 *  label: [field] ([icon])
 *
 */
public class NameFieldButtonOption implements BladeOption<StringProperty> {
	private final String label;
	private final TooltipIconOptions iconOptions;
	private final boolean readOnly;

	public NameFieldButtonOption(String label, TooltipIconOptions iconOptions, boolean readOnly) {
		this.label = label;
		this.iconOptions = iconOptions;
		this.readOnly = readOnly;
	}

	public String getLabel() {
		return label;
	}

	public TooltipIconOptions getIconOptions() {
		return iconOptions;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}
}
