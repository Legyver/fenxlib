package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXPaginationOptions;
import io.github.palexdev.materialfx.controls.MFXPagination;

/**
 * Factory to produce a MFXPagination control
 */
public class MFXPaginationFactory implements NodeFactory<MFXPagination, MFXPaginationOptions> {

    @Override
    public MFXPagination makeNode(LocationContext locationContext, MFXPaginationOptions options) throws CoreException {
        MFXPagination pagination;
        Integer maxPage = options.getMaxPage();
        Integer pagesToShow = options.getPagesToShow();
        if (maxPage != null && pagesToShow != null) {
            pagination = new MFXPagination(maxPage, pagesToShow);
        } else if (maxPage != null) {
            pagination = new MFXPagination(maxPage);
        } else {
            pagination = new MFXPagination();
        }
        Fenxlib.register(locationContext.decorateWith(options.getName()), pagination);
        return pagination;
    }

    @Override
    public MFXPaginationOptions newOptions() {
        return new MFXPaginationOptions();
    }
}
