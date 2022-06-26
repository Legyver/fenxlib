package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.base.AbstractMFXTreeCell;
import io.github.palexdev.materialfx.controls.base.AbstractMFXTreeItem;
import javafx.util.Callback;

public class MFXTreeItemOptions<T> extends BaseControlBuilder<MFXTreeItemOptions> implements StyleableControlOptions<MFXTreeItem> {
    private T data;
    private Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> cellFactory;

    public MFXTreeItemOptions<T> data(T data) {
        this.data = data;
        return me();
    }

    public T getData() {
        return data;
    }

    public MFXTreeItemOptions<T> cellFactory(Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> cellFactory) {
        this.cellFactory = cellFactory;
        return me();
    }

    public Callback<AbstractMFXTreeItem<T>, AbstractMFXTreeCell<T>> getCellFactory() {
        return cellFactory;
    }
}
