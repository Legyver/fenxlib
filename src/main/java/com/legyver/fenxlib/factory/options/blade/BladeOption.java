package com.legyver.fenxlib.factory.options.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.Observable;

public interface BladeOption<T extends Observable> {

	void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException;

}
