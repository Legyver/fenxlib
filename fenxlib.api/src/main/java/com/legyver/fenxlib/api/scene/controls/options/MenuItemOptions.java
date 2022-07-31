package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.EventHandlerMixin;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Options for a JavaFX MenuItem
 */
public class MenuItemOptions extends BaseControlBuilder<MenuItemOptions> implements StyleableControlOptions<MenuItem>,
        TextMixin<MenuItemOptions>,
        EventHandlerMixin<MenuItemOptions> {
    private String text;
    private EventHandler eventHandler;

}
