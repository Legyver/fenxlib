package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.options.PopupOptions;
import com.legyver.fenxlib.core.controls.popup.ButtonPopup;
import com.legyver.fenxlib.core.controls.popup.Popup;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Collections;
import java.util.List;

/**
 * Factory to create a popup
 */
public class PopupFactory implements NodeFactory<Popup, PopupOptions> {

    @Override
    public Popup makeNode(LocationContext locationContext, PopupOptions options) throws CoreException {
        Popup popup = makePopup();
        popup.setContent(options.getContent());
        List<Button> buttons = makeButtons();
        if (buttons != null && popup instanceof ButtonPopup) {
            ObservableList<Button> popupButtons = ((ButtonPopup) popup).getButtons();
            for (Button button : buttons) {
                popupButtons.add(button);
            }
        }
        return popup;
    }

    @Override
    public PopupOptions newOptions() {
        return new PopupOptions();
    }

    /**
     * Instantiate a Popup
     * @return a new Popup
     */
    protected Popup makePopup() {
        return new Popup();
    }

    /**
     * Make the buttons for the Popup
     * @return a list of Buttons to place on the Popup
     */
    protected List<Button> makeButtons() {
        return Collections.emptyList();
    }
}
