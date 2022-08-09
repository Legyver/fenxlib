package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.EventHandlerOptionMixin;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Options for a JavaFX MenuItem
 */
public class MenuItemOptions extends BaseControlBuilder<MenuItemOptions> implements StyleableControlOptions<MenuItem>,
        TextOptionMixin<MenuItemOptions>,
        EventHandlerOptionMixin<MenuItemOptions> {
    private String text;
    private Object[] textArgs;
    private EventHandler eventHandler;

}
