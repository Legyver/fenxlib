package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.ButtonFactory;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Button;

/**
 * Factory to make a MaterialFX button
 */
public class MFXButtonFactory extends ButtonFactory<MFXButton> {
    /**
     * Construct a Button.
     *
     * @param text          the text for the button
     * @param cancelButton  true if the button is a cancel button
     * @param defaultButton true if the button is a default button
     */
    public MFXButtonFactory(String text, Boolean cancelButton, Boolean defaultButton) {
        super(text, cancelButton, defaultButton);
    }

    protected Button makeButton() {
        return new MFXButton();
    }
}
