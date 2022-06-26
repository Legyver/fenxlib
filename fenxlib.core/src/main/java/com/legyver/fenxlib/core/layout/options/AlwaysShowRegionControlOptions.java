package com.legyver.fenxlib.core.layout.options;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;

/**
 * Options to always show control
 */
public class AlwaysShowRegionControlOptions implements IRegionControl {
    /**
     * Property that controls showing or hiding a region
     */
    private final BooleanProperty showRegion = new ReadOnlyBooleanWrapper(true);

    @Override
    public boolean isShowRegion() {
        return showRegion.get();
    }

    @Override
    public BooleanProperty showRegionProperty() {
        return showRegion;
    }

    @Override
    public void setShowRegion(boolean showRegion) {
        //noop
    }
}
