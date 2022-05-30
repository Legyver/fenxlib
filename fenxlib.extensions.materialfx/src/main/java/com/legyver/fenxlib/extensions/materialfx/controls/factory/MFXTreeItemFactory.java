package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.base.AbstractMFXTreeCell;
import io.github.palexdev.materialfx.controls.base.AbstractMFXTreeItem;
import javafx.util.Callback;

/**
 * Factory to produce MFXTreeItem
 * @param <T> type of data in tree item
 */
public class MFXTreeItemFactory<T> implements NodeFactory<MFXTreeItem<T>> {
    /**
     * Constructor param to pass data to the Tree Item
     */
    public static final String CONSTRUCTOR_PARAM_DATA = "data";
    /**
     * Constructor param to specify a cell factory for the Tree Item
     */
    public static final String CONSTRUCTOR_PARAM_CELL_FACTORY = "cellFactory";

    private final T data;
    private final Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> cellFactory;

    /**
     * Construct a factory to make a MFXTreeItem
     * @param data the data in the tree item
     * @param cellFactory cell factory
     */
    public MFXTreeItemFactory(T data, Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> cellFactory) {
        this.data = data;
        this.cellFactory = cellFactory;
    }

    @Override
    public MFXTreeItem makeNode(LocationContext locationContext) throws CoreException {
        MFXTreeItem mfxTreeItem;
        if (cellFactory == null) {
            mfxTreeItem = new MFXTreeItem(data);
        } else {
            mfxTreeItem = new MFXTreeItem(data, cellFactory);
        }
        return mfxTreeItem;
    }
}
