package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.options.SimpleIconOptions;
import com.legyver.fenxlib.widgets.blade.factory.options.SpinnerIconOptions;
import com.legyver.fenxlib.widgets.blade.factory.options.TooltipIconOptions;

/**
 * Visitor for applying icon options
 */
public interface IconOptionVisitor {

	/**
	 * Apply SimpleIconOptions
	 * @param iconOptions icon options to supply
	 * @throws CoreException if the implementing method throws an exception
	 */
	void visit(SimpleIconOptions iconOptions) throws CoreException;

	/**
	 * Apply TooltipIconOptions
	 * @param iconOptions icon options to apply
	 * @throws CoreException if the implementing method throws an exception
	 */
	void visit(TooltipIconOptions iconOptions) throws CoreException;

	/**
	 * Apply SpinnerIconOptions
	 * @param iconOptions icon options to apply
	 * @throws CoreException if the implementing method throws an exception
	 */
	void visit(SpinnerIconOptions iconOptions) throws CoreException;
}
