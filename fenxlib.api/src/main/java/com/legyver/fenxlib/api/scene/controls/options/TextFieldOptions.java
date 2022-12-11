package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.*;
import com.legyver.fenxlib.api.controls.builder.mixin.*;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/**
 * Options for a JavaFX TextField
 */
public class TextFieldOptions extends BaseControlBuilder<TextFieldOptions> implements StyleableControlOptions<TextField>,
        TextOptionMixin<TextFieldOptions>,
        PromptTextMixin<TextFieldOptions>,
        TextPropertyOptionMixin<TextFieldOptions>,
        EditableOptionMixin<TextFieldOptions>,
        EditablePropertyOptionMixin<TextFieldOptions> {
    private String text;
    private Object[] textArgs;
    private StringProperty textProperty;
    private Boolean editable;
    private BooleanProperty editableProperty;
    private String promptText;
}
