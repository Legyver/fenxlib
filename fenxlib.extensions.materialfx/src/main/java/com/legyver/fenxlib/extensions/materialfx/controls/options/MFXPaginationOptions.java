package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXPagination;

/**
 * Options for a MFXPagination control
 */
public class MFXPaginationOptions extends BaseControlBuilder<MFXPaginationOptions> implements StyleableControlOptions<MFXPagination> {
    private Integer maxPage;
    private Integer pagesToShow;

    /**
     * Specify the maximum page
     * @param maxPage the maximum page
     * @return this builder
     */
    public MFXPaginationOptions maxPage(Integer maxPage) {
        this.maxPage = maxPage;
        return me();
    }

    /**
     * Get the max page
     * @return the max page
     */
    public Integer getMaxPage() {
        return maxPage;
    }

    /**
     * Specify the pages to show
     * @param pagesToShow the pages to show
     * @return this builder
     */
    public MFXPaginationOptions pagesToShow(Integer pagesToShow) {
        this.pagesToShow = pagesToShow;
        return me();
    }

    /**
     * Get the pages to show
     * @return the pages to show
     */
    public Integer getPagesToShow() {
        return pagesToShow;
    }
    
}
