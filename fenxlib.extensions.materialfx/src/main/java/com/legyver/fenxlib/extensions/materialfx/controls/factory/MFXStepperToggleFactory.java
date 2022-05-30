package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import javafx.scene.Node;

/**
 * Factory to produce MFXStepperToggle
 */
public class MFXStepperToggleFactory implements NodeFactory<MFXStepperToggle> {
    /**
     * Param to specify the text for a MFXStepperToggle
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Param to specify the icon for a MFXStepperToggle
     */
    public static final String CONSTRUCTOR_PARAM_ICON = "icon";
    /**
     * Param to specify the content for a MFXStepperToggle
     */
    public static final String CONSTRUCTOR_PARAM_CONTENT = "content";

    private final String text;
    private final Node icon;
    private final Node content;

    /**
     * Construct a factory to produce a MFXStepperToggle
     * @param text the text for the toggle
     * @param icon the icon for the toggle
     * @param content the content of the toggle
     */
    public MFXStepperToggleFactory(String text, Node icon, Node content) {
        this.text = text;
        this.icon = icon;
        this.content = content;
    }

    @Override
    public MFXStepperToggle makeNode(LocationContext locationContext) throws CoreException {
        MFXStepperToggle stepperToggle = new MFXStepperToggle(text, icon, content);
        Fenxlib.register(locationContext, stepperToggle);
        return stepperToggle;
    }
}
