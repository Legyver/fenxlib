package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.text.Text;

/**
 * Factory to create a Text field
 */
public class TextFactory implements StyleableFactory<Text> {
    /**
     * Constructor parameter for the text value to set by default on the text.
     */
    public static final String TEXT = "text";
    private final String text;

    /**
     * Construct a TextFactory to create Text with specified text
     * @param text the text to use
     */
    public TextFactory(String text) {
        this.text = text;
    }

    @Override
    public Text makeNode(LocationContext locationContext) throws CoreException {
        Text text = makeText();
        text.setText(this.text);
        return text;
    }

    /**
     * Create a generic JavaFX Text field.  Override if you want something else.
     * @return a new text field.
     */
    protected Text makeText() {
        return new Text();
    }
}
