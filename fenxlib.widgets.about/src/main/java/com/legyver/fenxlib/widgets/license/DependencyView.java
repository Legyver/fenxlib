package com.legyver.fenxlib.widgets.license;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * A control to display the details of a library and its license
 */
public class DependencyView extends Control {
    private final DependencyData item;

    /**
     * Construct a License view to display the details of a dependency and its license
     * @param item the dependency
     */
    public DependencyView(DependencyData item) {
        this.item = item;
    }

    /**
     * Get the dependency data
     * @return the data
     */
    public DependencyData getItem() {
        return item;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new DependencyViewSkin(this);
    }
}
