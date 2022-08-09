package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.*;
import com.legyver.fenxlib.api.controls.builder.mixin.EditableOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.EditablePropertyOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextPropertyOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

/**
 * Options for a JavaFX TextArea
 */
public class TextAreaOptions extends BaseControlBuilder<TextAreaOptions> implements StyleableControlOptions<TextArea>,
        TextOptionMixin<TextAreaOptions>,
        TextPropertyOptionMixin<TextAreaOptions>,
        EditableOptionMixin<TextAreaOptions>,
        EditablePropertyOptionMixin<TextAreaOptions> {
    private String text;
    private Object[] textArgs;
    private StringProperty textProperty;
    private Boolean editable;
    private BooleanProperty editableProperty;

}
