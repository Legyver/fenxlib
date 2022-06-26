package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.ButtonFactory;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Button;

/**
 * Factory to make a MaterialFX button
 */
public class MFXButtonFactory extends ButtonFactory<MFXButton> {

    protected Button makeButton() {
        return new MFXButton();
    }
}
