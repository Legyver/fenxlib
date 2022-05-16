package com.legyver.fenxlib.core.factory.adapters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Adapter to handle setting values on controls that have an items property
 */
public class ItemsAdapter extends NotNullAdapter<ObservableList> {

    /**
     * Construct an adaptor for passing the items to a control
     * @param items the items for the control.
     *
     */
    public ItemsAdapter(List items) {
        super(adapt(items));
    }

    private static ObservableList adapt(List items) {
        ObservableList adapted;
        if (items == null) {
            adapted = null;
        } else if (items instanceof ObservableList) {
            adapted = (ObservableList) items;
        } else {
            adapted = FXCollections.observableArrayList();
            for (Object item : items) {
                adapted.add(item);
            }
        }
        return adapted;
    }


}
