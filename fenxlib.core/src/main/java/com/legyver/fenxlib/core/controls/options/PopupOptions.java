package com.legyver.fenxlib.core.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.core.controls.popup.Popup;
import javafx.scene.Node;

public class PopupOptions extends BaseControlBuilder<PopupOptions> implements StyleableControlOptions<Popup>, NodeContentMixin<PopupOptions> {
    Node content;
}
