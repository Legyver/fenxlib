package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ItemsMixin;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXTableViewOptions;
import io.github.palexdev.materialfx.controls.MFXTableView;

import java.util.List;

/**
 * Factory to produce a MFXTableView
 */
public class MFXTableViewFactory implements NodeFactory<MFXTableView, MFXTableViewOptions> {

    @Override
    public MFXTableView makeNode(LocationContext locationContext, MFXTableViewOptions options) throws CoreException {
        MFXTableView mfxTableView = makeTableView();
        options.itemsAdapter().setNotNull(mfxTableView::setItems);
        Fenxlib.register(locationContext.decorateWith(options.getName()), mfxTableView);
        return mfxTableView;
    }

    @Override
    public MFXTableViewOptions newOptions() {
        return new MFXTableViewOptions();
    }

    /**
     * Factory method to create a MFXTableView
     * @return a new MFXTableView
     */
    protected MFXTableView makeTableView() {
        return new MFXTableView();
    }
}
