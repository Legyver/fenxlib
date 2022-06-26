package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

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
