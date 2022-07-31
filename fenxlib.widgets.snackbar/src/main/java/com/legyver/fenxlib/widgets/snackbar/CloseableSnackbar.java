package com.legyver.fenxlib.widgets.snackbar;

import com.legyver.fenxlib.api.alert.ICloseableAlert;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Skin;

/**
 * Closeable version of a snackbar that adds in a close button
 */
public class CloseableSnackbar extends Snackbar implements ICloseableAlert {
    /**
     * Property to be notified if the snackbar should be closed
     */
    private final BooleanProperty close = new SimpleBooleanProperty(false);

    /**
     * Construct a Closeable snackbar that auto-closes after a specified timeout
     * @param timeout the timeout before auto-closing the snackbar
     */
    public CloseableSnackbar(Long timeout) {
        super(timeout);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CloseableSnackbarSkin(this);
    }

    /**
     * Check if the snackbar is closed
     * @return true if the snackbar is closed
     */
    public boolean isClose() {
        return close.get();
    }

    @Override
    public BooleanProperty closeProperty() {
        return close;
    }

    /**
     * Set the close flag
     * @param close true if you want to close the snackbar
     */
    public void setClose(boolean close) {
        this.close.set(close);
    }
}
