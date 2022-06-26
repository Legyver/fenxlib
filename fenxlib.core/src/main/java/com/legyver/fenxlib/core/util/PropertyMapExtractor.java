package com.legyver.fenxlib.core.util;

import com.legyver.fenxlib.core.locator.IPropertyAware;
import javafx.collections.ObservableMap;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.stage.Window;

/**
 * Extract the properties map for JavaFX controls that do not have a common ancestor
 */
public class PropertyMapExtractor {
    private final EventTarget eventTarget;

    /**
     * Construct an extractor to get the property map from any one of a number of JavaFX (or custom) controls
     * @param eventTarget the target to extract the property map from
     */
    public PropertyMapExtractor(EventTarget eventTarget) {
        this.eventTarget = eventTarget;
    }

    /**
     * Retrieve the property map. If none exists will return null.
     * @return the property map from the JavaFX (or custom) control, or null
     */
    public ObservableMap<Object, Object> get() {
        ObservableMap<Object, Object> result = null;
        if (eventTarget instanceof MenuItem) {
            result = ((MenuItem) eventTarget).getProperties();
        } else if (eventTarget instanceof Node) {
            result = ((Node) eventTarget).getProperties();
        } else if (eventTarget instanceof Tab) {
            result = ((Tab) eventTarget).getProperties();
        } else if (eventTarget instanceof Window) {
            result = ((Window) eventTarget).getProperties();
        } else if (eventTarget instanceof IPropertyAware) {
            result = ((IPropertyAware) eventTarget).getProperties();
        }

        return result;
    }
}
