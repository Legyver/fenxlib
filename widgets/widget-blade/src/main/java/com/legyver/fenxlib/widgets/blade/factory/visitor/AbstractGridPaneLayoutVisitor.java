package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.core.locator.LocationContext;

public abstract class AbstractGridPaneLayoutVisitor {
	protected final LocationContext locationContext;
	protected final BladeContext bladeContext;

	public AbstractGridPaneLayoutVisitor(LocationContext locationContext, BladeContext bladeContext) {
		this.locationContext = locationContext;
		this.bladeContext = bladeContext;
	}

	public abstract void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException;
	public abstract void visit(AbstractNameFieldOption nameFieldOption, int row);
	public abstract void visit(NameListClickOption nameListClickOption, int row);
	public abstract void visit(NameDatePickerOption nameDatePickerOption, int row);
	public abstract void visit(ShowMoreLabelOption showMoreOptions, int row) throws CoreException;
}
