package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;

public class TextFlowFactory extends AbstractWrappingFactory implements NodeFactory<TextFlow>  {

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
