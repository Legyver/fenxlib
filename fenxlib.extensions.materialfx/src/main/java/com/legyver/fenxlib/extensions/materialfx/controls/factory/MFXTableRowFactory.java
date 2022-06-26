package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXTableRowOptions;
import io.github.palexdev.materialfx.controls.MFXTableRow;

/**
 * Factory to produce a MFXTableRow
 */
public class MFXTableRowFactory implements NodeFactory<MFXTableRow, MFXTableRowOptions<?>> {

    @Override
    public MFXTableRow makeNode(LocationContext locationContext, MFXTableRowOptions options) throws CoreException {
        MFXTableRow mfxTableRow = new MFXTableRow<>(options.getTableView(), options.getData());
        Fenxlib.register(locationContext.decorateWith(options.getName()), mfxTableRow);
        return mfxTableRow;
    }

    @Override
    public MFXTableRowOptions<?> newOptions() {
        return new MFXTableRowOptions<>();
    }
}
