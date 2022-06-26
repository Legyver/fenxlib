package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.scene.Node;

public interface GraphicMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T graphic(Node graphic) {
        new ReflectionOperator(builder()).setValue("graphic", graphic);
        return builder();
    }

    default Node getGraphic() {
        return (Node) new ReflectionOperator(builder()).getValue("graphic");
    }
}
