package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ItemsMixin;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXListViewOptions;
import io.github.palexdev.materialfx.controls.MFXListView;

import java.util.List;

/**
 * Factory to produce a MFXListView
 */
public class MFXListViewFactory implements NodeFactory<MFXListView, MFXListViewOptions> {

    @Override
    public MFXListView makeNode(LocationContext locationContext, MFXListViewOptions options) throws CoreException {
        MFXListView listView = new MFXListView();
        options.itemsAdapter().setNotNull(listView::setItems);
        Fenxlib.register(locationContext, listView);
        return listView;
    }

    @Override
    public MFXListViewOptions newOptions() {
        return new MFXListViewOptions();
    }
}
