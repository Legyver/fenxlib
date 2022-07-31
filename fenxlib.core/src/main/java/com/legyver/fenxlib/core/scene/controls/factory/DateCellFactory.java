package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.DateCellOptions;
import javafx.scene.control.DateCell;

/**
 * Factory to create a DateCell control
 */
public class DateCellFactory implements NodeFactory<DateCell, DateCellOptions> {

    @Override
    public DateCell makeNode(LocationContext locationContext, DateCellOptions options) throws CoreException {
        DateCell dateCell = makeDateCell();
        Fenxlib.register(locationContext.decorateWith(options.getName()), dateCell);
        return dateCell;
    }

    @Override
    public DateCellOptions newOptions() {
        return new DateCellOptions();
    }

    /**
     * Factory method to instantiate a DateCell.
     * @return a javafx DateCell by default, override if you need something else
     */
    protected DateCell makeDateCell() {
        return new DateCell();
    }
}
