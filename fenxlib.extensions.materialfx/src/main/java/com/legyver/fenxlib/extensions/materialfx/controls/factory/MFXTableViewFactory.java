package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import io.github.palexdev.materialfx.controls.MFXTableView;

import java.util.List;

/**
 * Factory to produce a MFXTableView
 */
public class MFXTableViewFactory implements NodeFactory<MFXTableView> {
    /**
     * Constructor param to specify items for the MFXTableView
     */
    public static final String CONSTRUCTOR_PARAM_ITEMS = "items";

    private final ItemsAdapter items;

    /**
     * Construct a factory to produce MFXTableView
     * @param items any items to set on the table view
     */
    public MFXTableViewFactory(List items) {
        this.items = new ItemsAdapter(items);
    }

    @Override
    public MFXTableView makeNode(LocationContext locationContext) throws CoreException {
        MFXTableView mfxTableView = makeTableView();
        items.setNotNull(mfxTableView::setItems);
        Fenxlib.register(locationContext, mfxTableView);
        return mfxTableView;
    }

    /**
     * Factory method to create a MFXTableView
     * @return a new MFXTableView
     */
    protected MFXTableView makeTableView() {
        return new MFXTableView();
    }
}
