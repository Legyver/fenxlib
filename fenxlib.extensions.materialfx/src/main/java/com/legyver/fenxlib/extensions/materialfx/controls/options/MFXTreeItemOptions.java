package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.base.AbstractMFXTreeCell;
import io.github.palexdev.materialfx.controls.base.AbstractMFXTreeItem;
import javafx.util.Callback;

/**
 * Options for a MFXTreeItem control
 */
public class MFXTreeItemOptions<T> extends BaseControlBuilder<MFXTreeItemOptions> implements StyleableControlOptions<MFXTreeItem> {
    private T data;
    private Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> cellFactory;

    /**
     * Specify the data to use
     * @param data the data
     * @return this builder
     */
    public MFXTreeItemOptions<T> data(T data) {
        this.data = data;
        return me();
    }

    /**
     * Get the data
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Specify a cell factory
     * @param cellFactory the factory
     * @return this builder
     */
    public MFXTreeItemOptions<T> cellFactory(Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> cellFactory) {
        this.cellFactory = cellFactory;
        return me();
    }

    /**
     * Get the cell factory
     * @return the cell factory
     */
    public Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> getCellFactory() {
        return cellFactory;
    }
}
