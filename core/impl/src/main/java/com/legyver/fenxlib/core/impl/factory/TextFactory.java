package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.text.Text;


/**
 * Factory to create a Text field
 */
public class TextFactory implements NodeFactory<Text> {
    private final String text;

    public TextFactory(String text) {
        this.text = text;
    }

    @Override
    public Text makeNode(LocationContext locationContext) throws CoreException {
        return new Text(text);
    }
}
