package com.legyver.fenxlib.core.layout.options;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Region control option to show or hide a region.
 * If at least one of the regions components is shown, the whole region is shown.
 * The region is hidden when all the regions components are hidden.
 */
public class AbstractRegionControlOptions implements IRegionControl {
    /**
     * Property that controls showing or hiding a region
     */
    private final BooleanProperty showRegion = new SimpleBooleanProperty(false);
    /**
     * Property that tracks the number of components of a region are shown
     */
    private final IntegerProperty shown = new SimpleIntegerProperty();
    /**
     * Component controls for the region.
     */
    private final ObservableList<IRegionComponentControl> regionComponentControls = FXCollections.observableArrayList();

    /**
     * Construct an RegionControl that registers listeners on the {@link #regionComponentControls}.
     * Each control is observed and an internal count maintained of the controls that are currently shown in a region.
     */
    public AbstractRegionControlOptions() {
        regionComponentControls.addListener((ListChangeListener<IRegionComponentControl>) c -> {
            c.next();
            //ensure an accurate count of shown objects
            if (c.wasAdded() || c.wasReplaced()) {
                for (IRegionComponentControl regionComponentControl : c.getAddedSubList()) {
                    regionComponentControl.showRegionComponentProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            shown.add(1);
                        } else {
                            shown.subtract(1);
                        }
                    });
                }
            }
        });
        shown.addListener((observable, oldValue, newValue) -> {
            //if the count of shown objects drops below 1, hide the region
            if (newValue.longValue() < 1L) {
                showRegion.set(false);
            } else {
                showRegion.set(true);
            }
        });
    }

    /**
     * Get the region component controls
     * @return the region component controls
     */
    public ObservableList<IRegionComponentControl> getRegionComponentControls() {
        return regionComponentControls;
    }

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
        this.showRegion.set(showRegion);
    }
}
