package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.popup.ButtonPopup;
import com.legyver.fenxlib.core.controls.popup.Popup;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Collections;
import java.util.List;

/**
 * Factory to create a popup
 */
public class PopupFactory implements NodeFactory<Popup> {
    /**
     * Constructor param to pass the popup content via a map
     */
    public static final String PARAM_CONTENT = "content";
    /**
     * Constructor param to pass the popup buttons via a map
     */
    public static final String PARAM_BUTTONS = "buttons";

    private final Node content;
    private final List<Button> buttons;

    /**
     * Construct a popup factory to create a popup with the specified content and buttons
     * @param content the content to display in the popup
     * @param buttons the buttons to display on the popup
     */
    public PopupFactory(Node content, List<Button> buttons) {
        this.content = content;
        this.buttons = buttons == null ? Collections.emptyList() : buttons;
    }

    @Override
    public Popup makeNode(LocationContext locationContext) throws CoreException {
        Popup popup = makePopup();
        popup.setContent(content);
        List<Button> buttons = makeButtons();
        if (buttons != null && popup instanceof ButtonPopup) {
            ObservableList<Button> popupButtons = ((ButtonPopup) popup).getButtons();
            for (Button button : buttons) {
                popupButtons.add(button);
            }
        }
        return popup;
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
        return buttons;
    }
}
