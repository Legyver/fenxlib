package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class TextFieldOptions extends BaseControlBuilder<TextFieldOptions> implements StyleableControlOptions<TextField>,
        TextMixin<TextFieldOptions>,
        TextPropertyMixin<TextFieldOptions>,
        EditableMixin<TextFieldOptions>,
        EditablePropertyMixin<TextFieldOptions> {
    private String text;
    private StringProperty textProperty;
    private Boolean editable;
    private BooleanProperty editableProperty;

}
