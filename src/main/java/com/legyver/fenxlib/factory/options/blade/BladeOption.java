package com.legyver.fenxlib.factory.options.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.Observable;

public interface BladeOption<T extends Observable> {

	//TODO: maybe the visitor here should just be an interface for extensibility
	void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException;

}
