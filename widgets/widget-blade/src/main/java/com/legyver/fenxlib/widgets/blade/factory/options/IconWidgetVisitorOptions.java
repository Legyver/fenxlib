package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;

public interface IconWidgetVisitorOptions {
	void accept(IconOptionVisitor visitor) throws CoreException;
}
