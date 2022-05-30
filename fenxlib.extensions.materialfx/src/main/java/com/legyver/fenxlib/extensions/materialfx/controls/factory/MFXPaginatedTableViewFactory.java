package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableView;

import java.util.List;

/**
 * Factory to produce a MFXPaginatedTableView
 */
public class MFXPaginatedTableViewFactory extends MFXTableViewFactory {
    /**
     * Construct a factory to produce MFXTableView
     *
     * @param items any items to set on the table view
     */
    public MFXPaginatedTableViewFactory(List items) {
        super(items);
    }

    @Override
    protected MFXTableView makeTableView() {
        return new MFXPaginatedTableView();
    }
}
