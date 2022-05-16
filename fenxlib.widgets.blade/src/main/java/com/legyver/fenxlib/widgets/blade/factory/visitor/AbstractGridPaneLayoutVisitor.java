package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;

/**
 * Superclass for the GridPane layout visitors
 */
public abstract class AbstractGridPaneLayoutVisitor {
	/**
	 * Form location to register sub-components under
	 */
	protected final LocationContext locationContext;
	/**
	 * Contains information for laying out "more" fields on the form
	 */
	protected final BladeContext bladeContext;

	/**
	 * Construct a visitor to layout row-items on a form
	 * @param locationContext location to register as
	 * @param bladeContext blade context
	 */
	public AbstractGridPaneLayoutVisitor(LocationContext locationContext, BladeContext bladeContext) {
		this.locationContext = locationContext;
		this.bladeContext = bladeContext;
	}

	/**
	 * Position a text field with a button next to it in a blade form
	 * @param nameFieldButtonOption options for showing more fields
	 * @param row the row of the form this goes on
	 * @throws CoreException if there is a problem with any of the nested builders
	 */
	public abstract void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException;
	/**
	 * Layout a text field in a blade form
	 * @param nameFieldOption options for showing a labeled text field
	 * @param row the row of the form this goes on
	 * @throws CoreException if there is a problem with any of the nested builders
	 */
	public abstract void visit(AbstractNameFieldOption nameFieldOption, int row) throws CoreException;
	/**
	 * Layout a list field in a blade form
	 * @param nameListClickOption options for showing a labeled list field
	 * @param row the row of the form this goes on
	 * @throws CoreException if there is a problem with any of the nested builders
	 */
	public abstract void visit(NameListClickOption nameListClickOption, int row) throws CoreException;
	/**
	 * Layout a date field in a blade form
	 * @param nameDatePickerOption options for showing a labeled date field
	 * @param row the row of the form this goes on
	 * @throws CoreException if there is a problem with any of the nested builders
	 */
	public abstract void visit(NameDatePickerOption nameDatePickerOption, int row) throws CoreException;

	/**
	 * Layout a more field in a blade form
	 * @param showMoreOptions options for showing more fields
	 * @param row the row of the form this goes on
	 * @throws CoreException if there is a problem with any of the nested builders
	 */
	public abstract void visit(ShowMoreLabelOption showMoreOptions, int row) throws CoreException;
}
