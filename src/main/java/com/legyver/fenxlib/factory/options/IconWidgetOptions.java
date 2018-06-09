package com.legyver.fenxlib.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.visitor.IconOptionVisitor;

/**
 * This abstraction is necessary to support display of both icons and
 * JFXSpinners generically
 */
public interface IconWidgetOptions {
	void accept(IconOptionVisitor visitor) throws CoreException;
}
