package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.*;
import com.legyver.fenxlib.api.controls.builder.mixin.*;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

/**
 * Options for a JavaFX TextArea
 */
public class TextAreaOptions extends BaseControlBuilder<TextAreaOptions> implements StyleableControlOptions<TextArea>,
        TextOptionMixin<TextAreaOptions>,
        PromptTextMixin<TextAreaOptions>,
        TextPropertyOptionMixin<TextAreaOptions>,
        EditableOptionMixin<TextAreaOptions>,
        EditablePropertyOptionMixin<TextAreaOptions> {
    private String text;
    private Object[] textArgs;
    private StringProperty textProperty;
    private Boolean editable;
    private BooleanProperty editableProperty;
    private String promptText;

}
