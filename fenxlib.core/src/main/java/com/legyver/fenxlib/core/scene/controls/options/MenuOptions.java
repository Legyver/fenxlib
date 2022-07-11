package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import javafx.scene.control.Menu;

/**
 * Options for a JavaFX Menu
 */
public class MenuOptions extends BaseControlBuilder<MenuOptions> implements StyleableControlOptions<Menu>, TextMixin<MenuOptions> {
    private String text;
}
