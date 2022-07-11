package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.MFXTableView;

/**
 * Options for a MFXTableRow control
 */
public class MFXTableRowOptions<T> extends BaseControlBuilder<MFXTableRowOptions> implements StyleableControlOptions<MFXTableRow> {
    private MFXTableView<T> tableView;
    private T data;

    /**
     * Specify the table view to use for the table row
     * @param tableView the table view
     * @return this builder
     */
    public MFXTableRowOptions<T> tableView(MFXTableView<T> tableView) {
        this.tableView = tableView;
        return me();
    }

    /**
     * Get the table view
     * @return the table view
     */
    public MFXTableView<T> getTableView() {
        return tableView;
    }

    /**
     * Specify the data to use
     * @param data the data
     * @return this builder
     */
    public MFXTableRowOptions<T> data(T data) {
        this.data = data;
        return me();
    }

    /**
     * Get the data
     * @return the data
     */
    public T getData() {
        return data;
    }
}
