package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import io.github.palexdev.materialfx.controls.MFXListView;

import java.util.List;

/**
 * Factory to produce a MFXListView
 */
public class MFXListViewFactory implements NodeFactory<MFXListView> {
    /**
     * Constructor param to specify items for the MFXListView
     */
    public static final String CONSTRUCTOR_PARAM_ITEMS = "items";

    private final ItemsAdapter items;

    /**
     * Construct a factory to produce a MFXListView
     * @param items the items to place in the list view
     */
    public MFXListViewFactory(List items) {
        this.items = new ItemsAdapter(items);
    }

    @Override
    public MFXListView makeNode(LocationContext locationContext) throws CoreException {
        MFXListView listView = new MFXListView();
        items.setNotNull(listView::setItems);
        Fenxlib.register(locationContext, listView);
        return listView;
    }
}
