package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ChoiceBox;

import java.util.List;

/**
 * Factory to create a ChoiceBox control
 */
public class ChoiceBoxFactory implements NodeFactory<ChoiceBox> {

    /**
     * Constructor param to specify items for the ChoiceBox
     */
    public static final String CONSTRUCTOR_PARAM_ITEMS = "items";

    private final ItemsAdapter items;

    /**
     * Construct a choice box with the requisite items
     * @param items the items for the choice box.
     *              If no items are specified, the choice box will be empty.
     *              If the item list is an observable list, the items are passed to the choice box without further ado
     *              otherwise an observable list is constructed of the items, and that is passed to the choice box
     */
    public ChoiceBoxFactory(List items) {
        this.items = new ItemsAdapter(items);
    }

    @Override
    public ChoiceBox makeNode(LocationContext locationContext) throws CoreException {
        ChoiceBox choiceBox = makeChoiceBox();
        items.setNotNull(list -> choiceBox.setItems(list));
        Fenxlib.register(locationContext, choiceBox);
        return choiceBox;
    }

    /**
     * Factory method to instantiate a ChoiceBox.
     * @return a javafx ChoiceBox by default, override if you need something else
     */
    protected ChoiceBox makeChoiceBox() {
        return new ChoiceBox();
    }
}
