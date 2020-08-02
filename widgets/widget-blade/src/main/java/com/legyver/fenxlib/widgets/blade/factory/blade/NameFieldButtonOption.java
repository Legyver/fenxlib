package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.options.TooltipIconOptions;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.property.StringProperty;

/**
 * Used mainly for GridPanes where there are multiple rows of
 *  label: [field] ([icon])
 *
 */
public class NameFieldButtonOption extends AbstractBladeGridLayout implements BladeOption<StringProperty> {
	private final String label;
	private final TooltipIconOptions iconOptions;
	private final boolean readOnly;

	public NameFieldButtonOption(String label, TooltipIconOptions iconOptions, int labelSpan, boolean readOnly) {
		super(labelSpan, GRID_COLS - labelSpan - 1);
		this.label = label;
		this.iconOptions = iconOptions;
		this.readOnly = readOnly;
	}

	public NameFieldButtonOption(String label, TooltipIconOptions iconOptions, boolean readOnly) {
		this(label, iconOptions, 1, readOnly);
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
