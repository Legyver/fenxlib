package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableView;

import java.util.List;

/**
 * Factory to produce a MFXPaginatedTableView
 */
public class MFXPaginatedTableViewFactory extends MFXTableViewFactory {

    @Override
    protected MFXTableView makeTableView() {
        return new MFXPaginatedTableView();
    }
}
