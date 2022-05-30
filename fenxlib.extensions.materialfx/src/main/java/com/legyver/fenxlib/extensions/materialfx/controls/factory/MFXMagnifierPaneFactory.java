package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXMagnifierPane;
import javafx.scene.Node;

/**
 * Factory to produce a MFXMagnifierPane
 */
public class MFXMagnifierPaneFactory implements NodeFactory<MFXMagnifierPane> {
    /**
     * Param to specify the content to magnify
     */
    public static final String CONSTRUCTOR_PARAM_CONTENT = "content";
    private final Node content;

    /**
     * Construct a factory to produce a MFXMagnifierPane
     * @param content the content to magnify
     */
    public MFXMagnifierPaneFactory(Node content) {
        this.content = content;
    }

    @Override
    public MFXMagnifierPane makeNode(LocationContext locationContext) throws CoreException {
        MFXMagnifierPane magnifierPane = new MFXMagnifierPane(content);
        Fenxlib.register(locationContext, magnifierPane);
        return magnifierPane;
    }
}
