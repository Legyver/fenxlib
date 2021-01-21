package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;

/**
 * IconOptions that can be applied to icons via the Visitor pattern
 */
public interface IconWidgetVisitorOptions {
	/**
	 * Apply options to an icon
	 * @param visitor the visitor to accept
	 * @throws CoreException if the visitor throws an exception
	 */
	void accept(IconOptionVisitor visitor) throws CoreException;
}
