package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.PaginationOptions;
import javafx.scene.control.Pagination;

/**
 * Factory to create a Pagination control
 */
public class PaginationFactory implements NodeFactory<Pagination, PaginationOptions> {

    @Override
    public Pagination makeNode(LocationContext locationContext, PaginationOptions options) throws CoreException {
        Pagination pagination = makePagination();
        Fenxlib.register(locationContext.decorateWith(options.getName()), pagination);
        return pagination;
    }

    @Override
    public PaginationOptions newOptions() {
        return new PaginationOptions();
    }

    /**
     * Factory method to instantiate a Pagination.
     * @return a javafx Pagination by default, override if you need something else
     */
    protected Pagination makePagination() {
        return new Pagination();
    }
}
