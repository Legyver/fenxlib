package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

/**
 * Factory to product a MFXPasswordField
 */
public class MFXPasswordFieldFactory implements NodeFactory<MFXPasswordField> {
    /**
     * Constructor param to specify the default text for a password field
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Constructor param to specify the prompt text for a password field
     */
    public static final String CONSTRUCTOR_PARAM_PROMPT_TEXT = "promptText";
    /**
     * Constructor param to specify the floating text for a password field
     */
    public static final String CONSTRUCTOR_PARAM_FLOAT_TEXT = "floatText";

    private final String text;
    private final String promptText;
    private final String floatingText;

    /**
     * Construct a factory to produce a password field
     * @param text the default text
     * @param promptText the prompt text
     * @param floatingText the floating text
     */
    public MFXPasswordFieldFactory(String text, String promptText, String floatingText) {
        this.text = text;
        this.promptText = promptText;
        this.floatingText = floatingText;
    }

    @Override
    public MFXPasswordField makeNode(LocationContext locationContext) throws CoreException {
        MFXPasswordField passwordField;
        if (text != null && promptText != null && floatingText != null) {
            passwordField = new MFXPasswordField(text, promptText, floatingText);
        } else if (text != null && promptText != null) {
            passwordField = new MFXPasswordField(text, promptText);
        } else if (text != null) {
            passwordField = new MFXPasswordField(text);
        } else {
            passwordField = new MFXPasswordField();
        }
        Fenxlib.register(locationContext, passwordField);
        return passwordField;
    }
}
