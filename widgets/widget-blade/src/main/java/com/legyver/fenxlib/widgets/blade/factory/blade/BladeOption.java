package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.Observable;

/**
 * Options describing the layout of the grid
 * @param <T> the observable value that backs the form field
 */
public interface BladeOption<T extends Observable> {
	/**
	 * Accept the visitor for laying out the blade option
	 * @param visitor visitor to accept
	 * @param row the row to apply the layout to
	 * @throws CoreException if the visitor throws an exception
	 */
	//TODO: maybe the visitor here should just be an interface for extensibility
	void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException;

}
