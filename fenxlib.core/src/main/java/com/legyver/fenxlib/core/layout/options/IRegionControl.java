package com.legyver.fenxlib.core.layout.options;

import javafx.beans.property.BooleanProperty;

/**
 * Control that hides or shows a specific region based on how many of its components are shown.
 */
public interface IRegionControl {
    /**
     * Check if the region should be shown
     * @return true if the region should be shown
     */
    boolean isShowRegion();

    /**
     * Get the Observable property specifying if the region should be shown
     * @return the show region property
     */
    BooleanProperty showRegionProperty();

    /**
     * Set show region to a true or false
     * @param showRegion true if the region should be shown
     */
    void setShowRegion(boolean showRegion);
}
