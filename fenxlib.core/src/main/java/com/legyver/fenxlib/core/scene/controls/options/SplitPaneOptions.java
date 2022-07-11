package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ChildrenRegionsMixin;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Region;

import java.util.List;

/**
 * Options for a JavaFX SplitPane
 */
public class SplitPaneOptions extends BaseControlBuilder<SplitPaneOptions> implements StyleableControlOptions<SplitPane>,
        ChildrenRegionsMixin<SplitPaneOptions> {
    private Double splitPercentage;
    private Orientation orientation;
    private List<Region> children;

    /**
     * Specify the split percentage
     * @param splitPercentage the split percentage
     * @return this builder
     */
    public SplitPaneOptions splitPercentage(Double splitPercentage) {
        this.splitPercentage = splitPercentage;
        return me();
    }

    /**
     * Get the split percentage
     * @return the split percentage
     */
    public Double getSplitPercentage() {
        return splitPercentage;
    }

    /**
     * Specify the orientation
     * @param orientation the orientation
     * @return this builder
     */
    public SplitPaneOptions orientation(Orientation orientation) {
        this.orientation = orientation;
        return me();
    }

    /**
     * Get the orientation
     * @return the orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }
}
