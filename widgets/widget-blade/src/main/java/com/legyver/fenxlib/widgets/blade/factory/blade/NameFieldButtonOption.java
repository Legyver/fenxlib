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

	/**
	 * Construct the option for a button to be included in the form row next to a label and text field.
	 * @param label the label
	 * @param iconOptions the icon options for the button
	 * @param labelSpan the number of columns the label spans
	 * @param readOnly if the text field is read-only
	 */
	public NameFieldButtonOption(String label, TooltipIconOptions iconOptions, int labelSpan, boolean readOnly) {
		super(labelSpan, GRID_COLS - labelSpan - 1);
		this.label = label;
		this.iconOptions = iconOptions;
		this.readOnly = readOnly;
	}

	/**
	 * Construct the option for a button to be included in the form row next to a label and text field.
	 * @param label the label
	 * @param iconOptions the icon options for the button
	 * @param readOnly if the text field is read-only
	 */
	public NameFieldButtonOption(String label, TooltipIconOptions iconOptions, boolean readOnly) {
		this(label, iconOptions, 1, readOnly);
	}

	/**
	 * Get the field label
	 * @return the field label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Get the options for constructing the icon for the button
	 * @return the icon options
	 */
	public TooltipIconOptions getIconOptions() {
		return iconOptions;
	}

	/**
	 * Indicate if the field is read-only
	 * @return true if the field is read-only, otherwise false
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}
}
