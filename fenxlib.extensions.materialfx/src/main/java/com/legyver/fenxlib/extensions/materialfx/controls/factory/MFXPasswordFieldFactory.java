package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXPasswordFieldOptions;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

/**
 * Factory to product a MFXPasswordField
 */
public class MFXPasswordFieldFactory implements NodeFactory<MFXPasswordField, MFXPasswordFieldOptions> {

    @Override
    public MFXPasswordField makeNode(LocationContext locationContext, MFXPasswordFieldOptions options) throws CoreException {
        MFXPasswordField passwordField;
        String text = options.getText();
        String promptText = options.getPromptText();
        String floatingText = options.getFloatingText();
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

    @Override
    public MFXPasswordFieldOptions newOptions() {
        return new MFXPasswordFieldOptions();
    }

    public static class Options extends BaseControlBuilder<Options> {
        private String text;
        private String promptText;
        private String floatingText;

        public Options text(String text) {
            this.text = text;
            return me();
        }

        public String getText() {
            return text;
        }

        public Options promptText(String promptText) {
            this.promptText = promptText;
            return me();
        }


        public String getPromptText() {
            return promptText;
        }

        public Options floatingText(String floatingText) {
            this.floatingText = floatingText;
            return me();
        }

        public String getFloatingText() {
            return floatingText;
        }
    }
}
