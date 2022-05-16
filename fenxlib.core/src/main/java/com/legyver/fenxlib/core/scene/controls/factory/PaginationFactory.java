package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Pagination;

/**
 * Factory to create a Pagination control
 */
public class PaginationFactory implements NodeFactory<Pagination> {
    @Override
    public Pagination makeNode(LocationContext locationContext) throws CoreException {
        Pagination pagination = makePagination();
        Fenxlib.register(locationContext, pagination);
        return pagination;
    }

    /**
     * Factory method to instantiate a Pagination.
     * @return a javafx Pagination by default, override if you need something else
     */
    protected Pagination makePagination() {
        return new Pagination();
    }
}
