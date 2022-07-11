package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.EventHandlerMixin;
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
