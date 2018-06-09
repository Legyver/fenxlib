package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.SimpleIconOptions;
import com.legyver.fenxlib.factory.options.SpinnerIconOptions;
import com.legyver.fenxlib.factory.options.TooltipIconOptions;

public interface IconOptionVisitor {

	void visit(SimpleIconOptions iconOptions) throws CoreException;

	void visit(TooltipIconOptions iconOptions) throws CoreException;

	void visit(SpinnerIconOptions iconOptions) throws CoreException;
}
