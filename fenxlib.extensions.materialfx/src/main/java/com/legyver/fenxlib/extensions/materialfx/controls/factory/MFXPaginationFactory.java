package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXPagination;

/**
 * Factory to produce a MFXPagination control
 */
public class MFXPaginationFactory implements NodeFactory<MFXPagination> {
    /**
     * Param to specify the maxpage attribute of a MFXPagination control
     */
    public static final String CONSTRUCTOR_PARAM_MAX_PAGE = "maxPage";
    /**
     * Param to specify the pagesToShow attribute of a MFXPagination control
     */
    public static final String CONSTRUCTOR_PARAM_PAGES_TO_SHOW = "pagesToShow";

    private final Integer maxPage;
    private final Integer pagesToShow;

    /**
     * Construct a factory to produce a MFXPagination
     * @param maxPage the max page to show
     * @param pagesToShow the pages to show
     */
    public MFXPaginationFactory(Integer maxPage, Integer pagesToShow) {
        this.maxPage = maxPage;
        this.pagesToShow = pagesToShow;
    }

    @Override
    public MFXPagination makeNode(LocationContext locationContext) throws CoreException {
        MFXPagination pagination;
        if (maxPage != null && pagesToShow != null) {
            pagination = new MFXPagination(maxPage, pagesToShow);
        } else if (maxPage != null) {
            pagination = new MFXPagination(maxPage);
        } else {
            pagination = new MFXPagination();
        }
        Fenxlib.register(locationContext, pagination);
        return pagination;
    }
}
