package com.legyver.fenxlib.core.scene.text.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.controls.factory.AbstractWrappingFactory;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.util.map.MapBuilder;
import javafx.css.Styleable;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;

import java.util.List;

/**
 * Factory wrapper to encapsulate the output of the nested factories in a TextFlow
 */
public class TextFlowFactory extends AbstractWrappingFactory implements StyleableFactory<TextFlow> {

    /**
     * Construct a TextFlowFactory wrapping the output of any nested factories in a TextFlow
     * @param nodeFactories factories producing child content
     * @deprecated Use {@link ControlsFactory#make(Class, java.util.Map)}.
     * Use {@link TextFlowFactory#CHILDREN} to specify children.
     * It may behoove you to use {@link MapBuilder} for this.
     * This ultimately designates to {@link TextFlowFactory(List)}
     */
    @Deprecated
    public TextFlowFactory(NodeFactory... nodeFactories) {
        super(nodeFactories);
    }

    /**
     * Construct a TextFlow wrapping the specified pre-made elements.
     * Example:
     *   List children = makeThingsForTextFlow();
     *   ControlsFactory.make(TextFlow.class, MapBuilder.of(TextFlowFactory.CHILDREN, children));
     * @param children the pre-made children to wrap in the text flow
     */
    public TextFlowFactory(List<? extends Styleable> children) {
        super(children);
    }

    @Override
    public TextFlow makeNode(LocationContext locationContext) throws CoreException {
        TextFlow textFlow = makeTextFlow();
        textFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
        addChildren(textFlow.getChildren(), locationContext);
        return textFlow;
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
