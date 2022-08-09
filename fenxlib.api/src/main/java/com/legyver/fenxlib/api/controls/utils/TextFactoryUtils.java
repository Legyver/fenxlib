package com.legyver.fenxlib.api.controls.utils;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.api.scene.text.options.TextOptions;
import javafx.scene.text.Text;

import java.util.Locale;

/**
 * Convenience class to simplify making text content
 */
public class TextFactoryUtils {

    /**
     * Convenience method to get the {@link javafx.scene.text.Text}.
     * If there is no error, the text field will be constructed as normal from calling {@link ControlsFactory#make(Class, StyleableControlOptions)}.
     * If there is an error a text field will be returned with the text content internationalized.  All other {@link TextOptions} content is ignored.
     *
     * @param textOptions options containing specifications for the text control
     * @return the text control
     */
    public static Text getText(TextOptions textOptions) {
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
     * @param args optional arguments to apply to the text when resolving i18n messages
     * @return the text control
     */
    public static Text getText(String text, Object...args) {
        return getText(new TextOptions().text(text, args));
    }
}
