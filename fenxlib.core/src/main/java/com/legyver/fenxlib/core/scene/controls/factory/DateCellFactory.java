package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.DateCell;

/**
 * Factory to create a DateCell control
 */
public class DateCellFactory implements NodeFactory<DateCell> {
    @Override
    public DateCell makeNode(LocationContext locationContext) throws CoreException {
        DateCell dateCell = makeDateCell();
        Fenxlib.register(locationContext, dateCell);
        return dateCell;
    }

    /**
     * Factory method to instantiate a DateCell.
     * @return a javafx DateCell by default, override if you need something else
     */
    protected DateCell makeDateCell() {
        return new DateCell();
    }
}
