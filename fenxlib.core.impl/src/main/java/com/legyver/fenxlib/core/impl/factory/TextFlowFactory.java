package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;

/**
 * Factory wrapper to encapsulate the output of the nested factories in a TextFlow
 */
public class TextFlowFactory extends AbstractWrappingFactory implements NodeFactory<TextFlow> {

    public TextFlowFactory(NodeFactory... nodeFactories) {
        super(nodeFactories);
    }

    @Override
    public TextFlow makeNode(LocationContext locationContext) throws CoreException {
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
        addChildren(textFlow.getChildren(), locationContext);
        return textFlow;
    }
}
