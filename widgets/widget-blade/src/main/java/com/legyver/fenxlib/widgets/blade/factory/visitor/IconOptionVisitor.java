package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.options.SimpleIconOptions;
import com.legyver.fenxlib.widgets.blade.factory.options.SpinnerIconOptions;
import com.legyver.fenxlib.widgets.blade.factory.options.TooltipIconOptions;

public interface IconOptionVisitor {

	void visit(SimpleIconOptions iconOptions) throws CoreException;

	void visit(TooltipIconOptions iconOptions) throws CoreException;

	void visit(SpinnerIconOptions iconOptions) throws CoreException;
}
