package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXPagination;

public class MFXPaginationOptions extends BaseControlBuilder<MFXPaginationOptions> implements StyleableControlOptions<MFXPagination> {
    private Integer maxPage;
    private Integer pagesToShow;

    public MFXPaginationOptions maxPage(Integer maxPage) {
        this.maxPage = maxPage;
        return me();
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public MFXPaginationOptions pagesToShow(Integer pagesToShow) {
        this.pagesToShow = pagesToShow;
        return me();
    }

    public Integer getPagesToShow() {
        return pagesToShow;
    }
    
}
