package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ChildrenRegionsMixin;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Region;

import java.util.List;

public class SplitPaneOptions extends BaseControlBuilder<SplitPaneOptions> implements StyleableControlOptions<SplitPane>,
        ChildrenRegionsMixin<SplitPaneOptions> {
    private Double splitPercentage;
    private Orientation orientation;
    private List<Region> children;

    public SplitPaneOptions splitPercentage(Double splitPercentage) {
        this.splitPercentage = splitPercentage;
        return me();
    }

    public Double getSplitPercentage() {
        return splitPercentage;
    }

    public SplitPaneOptions orientation(Orientation orientation) {
        this.orientation = orientation;
        return me();
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
