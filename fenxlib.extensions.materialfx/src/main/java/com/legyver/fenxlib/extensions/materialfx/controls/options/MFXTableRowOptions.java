package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.MFXTableView;

public class MFXTableRowOptions<T> extends BaseControlBuilder<MFXTableRowOptions> implements StyleableControlOptions<MFXTableRow> {
    private MFXTableView<T> tableView;
    private T data;

    public MFXTableRowOptions<T> tableView(MFXTableView<T> tableView) {
        this.tableView = tableView;
        return me();
    }

    public MFXTableView<T> getTableView() {
        return tableView;
    }

    public MFXTableRowOptions<T> data(T data) {
        this.data = data;
        return me();
    }

    public T getData() {
        return data;
    }
}
