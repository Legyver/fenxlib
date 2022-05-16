package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ComboBox;

import java.util.List;

/**
 * Factory to create a ComboBox control
 */
public class ComboBoxFactory implements NodeFactory<ComboBox> {

    /**
     * Constructor param to specify items for the ComboBox
     */
    public static final String CONSTRUCTOR_PARAM_ITEMS = "items";

    private final ItemsAdapter items;

    /**
     * Construct a combo box with the requisite items
     * @param items the items for the combo box.
     *              If no items are specified, the choice box will be empty.
     *              If the item list is an observable list, the items are passed to the combo box without further ado
     *              otherwise an observable list is constructed of the items, and that is passed to the combo box
     */
    public ComboBoxFactory(List items) {
        this.items = new ItemsAdapter(items);
    }

    @Override
    public ComboBox makeNode(LocationContext locationContext) throws CoreException {
        ComboBox comboBox = makeComboBox();
        items.setNotNull(list -> comboBox.setItems(list));
        Fenxlib.register(locationContext, comboBox);
        return comboBox;
    }

    /**
     * Factory method to instantiate a ComboBox.
     * @return a javafx ComboBox by default, override if you need something else
     */
    protected ComboBox makeComboBox() {
        return new ComboBox();
    }
}
