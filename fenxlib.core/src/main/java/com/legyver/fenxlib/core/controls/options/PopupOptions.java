package com.legyver.fenxlib.core.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.NodeContentOptionMixin;
import com.legyver.fenxlib.core.controls.popup.Popup;
import javafx.scene.Node;

/**
 * Options for a Legyver Popup
 */
public class PopupOptions extends BaseControlBuilder<PopupOptions> implements StyleableControlOptions<Popup>, NodeContentOptionMixin<PopupOptions> {
    Node content;
}
