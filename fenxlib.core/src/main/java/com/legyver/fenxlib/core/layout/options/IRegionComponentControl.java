package com.legyver.fenxlib.core.layout.options;

import javafx.beans.property.BooleanProperty;

/**
 * Region controls to show/hide the specified region of the application
 */
public interface IRegionComponentControl {
    /**
     * Check if this component of the region is shown
     * @return True if this component of the region should be shown
     */
    boolean isShowRegionComponent();

    /**
     * Property providing an observable window into the visibility of a specific component in a region
     * @return the region component visibility flag property
     */
    BooleanProperty showRegionComponentProperty();

    /**
     * Set the show region component flag
     * @param showRegionComponent the value to set it to
     */
    void setShowRegionComponent(boolean showRegionComponent);
}
