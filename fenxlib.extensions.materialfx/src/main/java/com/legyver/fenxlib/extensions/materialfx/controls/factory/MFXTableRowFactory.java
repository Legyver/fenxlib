package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.MFXTableView;

/**
 * Factory to produce a MFXTableRow
 * @param <T> the type of the table/row data
 */
public class MFXTableRowFactory<T> implements NodeFactory<MFXTableRow> {
    /**
     * Param to specify a table view for the row
     */
    public static final String CONSTRUCTOR_PARAM_TABLE_VIEW = "tableView";
    /**
     * Param to specify the data for the row
     */
    public static final String CONSTRUCTOR_PARAM_DATA = "data";

    private final MFXTableView<T> tableView;
    private final T data;

    /**
     * Construct a factory to produce a MFXTableRow
     * @param tableView the table view
     * @param data the data
     */
    public MFXTableRowFactory(MFXTableView<T> tableView, T data) {
        this.tableView = tableView;
        this.data = data;
    }

    @Override
    public MFXTableRow makeNode(LocationContext locationContext) throws CoreException {
        MFXTableRow<T> mfxTableRow = new MFXTableRow<>(tableView, data);
        return mfxTableRow;
    }
}
