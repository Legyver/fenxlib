package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXTreeItemOptions;
import io.github.palexdev.materialfx.controls.MFXTreeItem;

/**
 * Factory to produce MFXTreeItem
 */
public class MFXTreeItemFactory implements NodeFactory<MFXTreeItem, MFXTreeItemOptions<?>> {

    @Override
    public MFXTreeItem makeNode(LocationContext locationContext, MFXTreeItemOptions options) throws CoreException {
        MFXTreeItem mfxTreeItem;
        if (options.getCellFactory() == null) {
            mfxTreeItem = new MFXTreeItem(options.getData());
        } else {
            mfxTreeItem = new MFXTreeItem(options.getData(), options.getCellFactory());
        }
        Fenxlib.register(locationContext.decorateWith(options.getName()), mfxTreeItem);
        return mfxTreeItem;
    }

    @Override
    public MFXTreeItemOptions<?> newOptions() {
        return new MFXTreeItemOptions<>();
    }
}
