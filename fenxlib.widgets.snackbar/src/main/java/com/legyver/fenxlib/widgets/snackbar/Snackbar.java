package com.legyver.fenxlib.widgets.snackbar;

import com.legyver.fenxlib.core.alert.IAlert;
import com.legyver.fenxlib.core.alert.Level;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Snackbar to display alerts.
 * This will, in general, have 2-3 sections
 * - The heading
 * - The body text
 * - (optional) buttons for disposing the alert
 */
public class Snackbar extends Control implements IAlert {

    /**
     * The message text.  This will be displayed in the body of the alert.
     */
    private final StringProperty message = new SimpleStringProperty();
    /**
     * The level of the alert. ie: INFO/WARNING/ERROR
     */
    private final ObjectProperty<Level> level = new SimpleObjectProperty<>();


    @Override
    protected Skin<?> createDefaultSkin() {
        return new SnackbarSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Snackbar.class.getResource("fenxlib-widgets-snackbar.css").toExternalForm();
    }

    /**
     * Get the message text.  This will be displayed in the body of the alert
     * @return the message text
     */
    public String getMessage() {
        return message.get();
    }

    /**
     * Get the message text property
     * @return the message text property
     */
    public StringProperty messageProperty() {
        return message;
    }

    /**
     * Set the message text property
     * @param message the message text
     */
    public void setMessage(String message) {
        this.message.set(message);
    }

    /**
     * Get the level for the alert.  ie: INFO/WARNING/ERROR
     * @return the level of the alert
     */
    public Level getLevel() {
        return level.get();
    }

    /**
     * Get the level property
     * @return the level property
     */
    public ObjectProperty<Level> levelProperty() {
        return level;
    }

    /**
     * Set the level property
     * @param level the level property
     */
    public void setLevel(Level level) {
        this.level.set(level);
    }

}
