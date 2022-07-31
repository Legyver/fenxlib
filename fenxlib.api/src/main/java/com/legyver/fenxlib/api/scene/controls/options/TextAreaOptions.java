package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.*;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

/**
 * Options for a JavaFX TextArea
 */
public class TextAreaOptions extends BaseControlBuilder<TextAreaOptions> implements StyleableControlOptions<TextArea>,
        TextMixin<TextAreaOptions>,
        TextPropertyMixin<TextAreaOptions>,
        EditableMixin<TextAreaOptions>,
        EditablePropertyMixin<TextAreaOptions> {
    private String text;
    private StringProperty textProperty;
    private Boolean editable;
    private BooleanProperty editableProperty;

}
