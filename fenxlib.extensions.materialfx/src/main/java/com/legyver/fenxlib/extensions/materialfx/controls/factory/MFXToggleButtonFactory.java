package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.scene.Node;

/**
 * Factory to produce a MFXToggleButton
 */
public class MFXToggleButtonFactory implements NodeFactory<MFXToggleButton> {
    /**
     * Param to specify the MFXToggleButton text
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Param to specify the MFXToggleButton graphic
     */
    public static final String CONSTRUCTOR_PARAM_GRAPHIC = "graphic";

    private final String text;
    private final Node graphic;

    /**
     * Construct a factory to produce a MFXToggleButton
     * @param text the text for the MFXToggleButton
     * @param graphic the graphic for the MFXToggleButton
     */
    public MFXToggleButtonFactory(String text, Node graphic) {
        this.text = text;
        this.graphic = graphic;
    }

    @Override
    public MFXToggleButton makeNode(LocationContext locationContext) throws CoreException {
        MFXToggleButton mfxToggleButton;
        if (text == null) {
            mfxToggleButton = new MFXToggleButton();
        } else {
            mfxToggleButton = new MFXToggleButton(text, graphic);
        }
        return mfxToggleButton;
    }
}
