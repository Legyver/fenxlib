package com.legyver.fenxlib.core.scene.text.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.text.options.TextOptions;
import javafx.scene.text.Text;

/**
 * Factory to create a Text field
 */
public class TextFactory implements StyleableFactory<Text, TextOptions> {

    @Override
    public Text makeNode(LocationContext locationContext, TextOptions textOptions) throws CoreException {
        Text text = makeText();
        text.setText(textOptions.getText());
        Fenxlib.register(locationContext.decorateWith(textOptions.getName()), text);
        return text;
    }

    @Override
    public TextOptions newOptions() {
        return new TextOptions();
    }

    /**
     * Create a generic JavaFX Text field.  Override if you want something else.
     * @return a new text field.
     */
    protected Text makeText() {
        return new Text();
    }
}
