package com.legyver.fenxlib.api.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.api.scene.text.options.TextOptions;
import javafx.scene.text.Text;

import java.util.Locale;

/**
 * Convenience mixin to simplify making text content
 */
public interface TextFactoryMixin {

    /**
     * Convenience method to get the {@link javafx.scene.text.Text}.
     * If there is no error, the text field will be constructed as normal from calling {@link ControlsFactory#make(Class, StyleableControlOptions)}.
     * If there is an error a text field will be returned with the text content internationalized.  All other {@link TextOptions} content is ignored.
     *
     * @param textOptions options containing specifications for the text control
     * @return the text control
     */
    default Text getText(TextOptions textOptions) {
        try {
            return ControlsFactory.make(Text.class, textOptions);
        } catch (CoreException coreException) {
            String i18n = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.getDefault(), textOptions.getText());
            return new Text(i18n);
        }
    }

    /**
     * Convenience method to get the {@link javafx.scene.text.Text}.
     * If there is no error, the text field will be constructed as normal from calling {@link ControlsFactory#make(Class, StyleableControlOptions)}.
     * If there is an error a text field will be returned with the text content internationalized.
     * @param text the text to display.
     * @return the text control
     */
    default Text getText(String text) {
        return getText(new TextOptions().text(text));
    }
}
