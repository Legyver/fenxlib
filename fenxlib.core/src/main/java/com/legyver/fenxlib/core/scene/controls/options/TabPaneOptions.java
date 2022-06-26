package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class TabPaneOptions extends BaseControlBuilder<TabPaneOptions> implements StyleableControlOptions<TabPane> {
    private List<Tab> tabs;

    public TabPaneOptions tabs(List<Tab> tabs) {
        this.tabs = tabs;
        return me();
    }

    public List<Tab> getTabs() {
        return tabs;
    }
}
