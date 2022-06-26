package com.legyver.fenxlib.core.scene.text.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.text.options.TextFlowOptions;
import com.legyver.fenxlib.core.util.LocationContextOperator;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;

import java.util.List;

/**
 * Factory wrapper to encapsulate the output of the nested factories in a TextFlow
 */
public class TextFlowFactory implements StyleableFactory<TextFlow, TextFlowOptions> {

    @Override
    public TextFlow makeNode(LocationContext locationContext, TextFlowOptions options) throws CoreException {
        TextFlow textFlow = makeTextFlow();
        textFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
        List<Node> children = options.getChildren();
        LocationContext flowContext = locationContext.decorateWith(options.getName());
        if (children != null) {
            for (int i = 0; i < children.size(); i ++) {
                Node child = children.get(i);
                new LocationContextOperator(child).reRegister(flowContext, "flow_" + options.getName() + "_child_" + i);
            }
            textFlow.getChildren().addAll(children);
        }
        return textFlow;
    }

    @Override
    public TextFlowOptions newOptions() {
        return new TextFlowOptions();
    }

    /**
     * Factory method to create a TextFlow.
     * By default, returns a plain javafx {@link TextFlow}. Override if you want something else.
     * @return the new TextFlow
     */
    protected TextFlow makeTextFlow() {
        return new TextFlow();
    }

}
