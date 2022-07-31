package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import javafx.scene.control.Menu;

/**
 * Options for a JavaFX Menu
 */
public class MenuOptions extends BaseControlBuilder<MenuOptions> implements StyleableControlOptions<Menu>, TextMixin<MenuOptions> {
    private String text;
}
