package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.scene.Node;

public class MFXToggleButtonOptions extends BaseControlBuilder<MFXToggleButtonOptions> implements StyleableControlOptions<MFXToggleButton> {
    private String text;
    private Node graphic;

    public MFXToggleButtonOptions text(String text) {
        this.text = text;
        return me();
    }

    public String getText() {
        return text;
    }

    public MFXToggleButtonOptions graphic(Node graphic) {
        this.graphic = graphic;
        return me();
    }

    public Node getGraphic() {
        return graphic;
    }
}
