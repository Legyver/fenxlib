package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ChoiceBoxOptions;
import javafx.scene.control.ChoiceBox;

/**
 * Factory to create a ChoiceBox control
 */
public class ChoiceBoxFactory implements NodeFactory<ChoiceBox, ChoiceBoxOptions> {

    @Override
    public ChoiceBox makeNode(LocationContext locationContext, ChoiceBoxOptions options) throws CoreException {
        ChoiceBox choiceBox = makeChoiceBox();
        options.itemsAdapter().setNotNull(list -> choiceBox.setItems(list));
        Fenxlib.register(locationContext.decorateWith(options.getName()), choiceBox);
        return choiceBox;
    }

    @Override
    public ChoiceBoxOptions newOptions() {
        return new ChoiceBoxOptions();
    }

    /**
     * Factory method to instantiate a ChoiceBox.
     * @return a javafx ChoiceBox by default, override if you need something else
     */
    protected ChoiceBox makeChoiceBox() {
        return new ChoiceBox();
    }
}
