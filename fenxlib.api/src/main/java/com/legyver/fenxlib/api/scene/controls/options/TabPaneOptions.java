package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

/**
 * Options for a JavaFX TabPane
 */
public class TabPaneOptions extends BaseControlBuilder<TabPaneOptions> implements StyleableControlOptions<TabPane> {
    private List<Tab> tabs;

    /**
     * Specify the tabs to use for the tab pane
     * @param tabs the tabs
     * @return this builder
     */
    public TabPaneOptions tabs(List<Tab> tabs) {
        this.tabs = tabs;
        return me();
    }

    /**
     * Get the tabs
     * @return the tabs
     */
    public List<Tab> getTabs() {
        return tabs;
    }
}
