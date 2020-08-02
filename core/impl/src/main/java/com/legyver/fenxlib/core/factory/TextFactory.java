package com.legyver.fenxlib.core.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.text.Text;

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
