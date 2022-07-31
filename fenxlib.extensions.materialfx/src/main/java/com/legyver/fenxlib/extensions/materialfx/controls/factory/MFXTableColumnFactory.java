package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXTableColumnOptions;
import io.github.palexdev.materialfx.controls.MFXTableColumn;

import java.util.Comparator;

/**
 * Factory to produce a MFXTableColumn
 */
public class MFXTableColumnFactory implements NodeFactory<MFXTableColumn, MFXTableColumnOptions> {

    @Override
    public MFXTableColumn makeNode(LocationContext locationContext, MFXTableColumnOptions options) throws CoreException {
        MFXTableColumn tableColumn = new MFXTableColumn();
        tableColumn.setText(options.getText());
        Boolean resizable = options.getResizable();
        if (resizable != null) {
            tableColumn.setColumnResizable(resizable);
        }
        Comparator comparator = options.getComparator();
        if (comparator != null) {
            tableColumn.setComparator(comparator);
        }
        Fenxlib.register(locationContext.decorateWith(options.getName()), tableColumn);
        return tableColumn;
    }

    @Override
    public MFXTableColumnOptions newOptions() {
        return new MFXTableColumnOptions();
    }
}
