package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import io.github.palexdev.materialfx.controls.MFXComboBox;

import java.util.List;

/**
 * Factory to produce a MFXComboBox
 */
public class MFXComboBoxFactory implements NodeFactory<MFXComboBox> {

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
    public MFXComboBoxFactory(List items) {
        this.items = new ItemsAdapter(items);
    }

    @Override
    public MFXComboBox makeNode(LocationContext locationContext) throws CoreException {
        MFXComboBox comboBox = makeComboBox();
        items.setNotNull(list -> comboBox.setItems(list));
        Fenxlib.register(locationContext, comboBox);
        return comboBox;
    }

    /**
     * factory method to produce a MFXComboBox
     * @return a new Combo Box
     */
    protected MFXComboBox makeComboBox() {
        return new MFXComboBox();
    }
}
