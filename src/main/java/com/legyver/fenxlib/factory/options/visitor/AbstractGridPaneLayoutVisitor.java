package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.factory.options.blade.NameSelectOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.factory.options.blade.UnlabeledEditableTextOption;
import com.legyver.fenxlib.locator.LocationContext;

public abstract class AbstractGridPaneLayoutVisitor {

	protected final LocationContext locationContext;
	protected final BladeContext bladeContext;

	public AbstractGridPaneLayoutVisitor(LocationContext locationContext, BladeContext bladeContext) {
		this.locationContext = locationContext;
		this.bladeContext = bladeContext;
	}

	public abstract void visit(NameFieldButtonOption option, int row) throws CoreException;

	public abstract void visit(AbstractNameFieldOption option, int row);

	public abstract void visit(NameListClickOption option, int row);

	public abstract void visit(NameDatePickerOption option, int row);

	public abstract void visit(ShowMoreLabelOption option, int row) throws CoreException;

	public abstract void visit(UnlabeledEditableTextOption option, int row);

	public abstract void visit(NameSelectOption option, int row);
}
