package com.legyver.fenxlib.core.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.*;
import com.legyver.fenxlib.core.controls.popup.Popup;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.menu.factory.MenuItemFactory;
import com.legyver.fenxlib.core.menu.factory.PopupMenuItemFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Instantiate default JavaFX controls with raw JavaFX implementation
 */
public class DefaultNodeInstantiationService implements NodeInstantiationService {

    private static final Map<Class, Function<Map, StyleableFactory>> instantiators = new HashMap<>();

    static {
        instantiators.put(StackPane.class, map -> new SystemBrowserHyperlinkFactory((String) map.get(SystemBrowserHyperlinkFactory.URL), null));
        instantiators.put(Hyperlink.class, map -> new SystemBrowserHyperlinkFactory((String) map.get(SystemBrowserHyperlinkFactory.URL), null));
        instantiators.put(Label.class, map -> new LabelFactory(
                (StringProperty) map.get(LabelFactory.BIND_TO),
                (String) map.get(LabelFactory.DEFAULT_TEXT)
        ));
        instantiators.put(Text.class, map -> new TextFactory((String) map.get(TextFactory.TEXT)));
        instantiators.put(TextField.class, map -> new TextFieldFactory(
                (StringProperty) map.get(TextFieldFactory.BIND_TO),
                (String) map.get(TextFieldFactory.DEFAULT_TEXT),
                (BooleanProperty) map.get(TextFieldFactory.BIND_TO_EDITABLE),
                (Boolean) map.get(TextFieldFactory.IS_EDITABLE)
        ));
        instantiators.put(TextFlow.class, map -> new TextFlowFactory((List<Styleable>) map.get(TextFlowFactory.CHILDREN)));
        instantiators.put(TitledPane.class, map -> new TitledPaneFactory(
                (String) map.get(TitledPaneFactory.CONTENT),
                (Pane) map.get(TitledPaneFactory.CONTENT)
        ));
        instantiators.put(TabPane.class, map -> new TabPaneFactory((List<? extends Tab>) map.get(TabPaneFactory.TABS)));
        instantiators.put(VBox.class, map -> new VBoxFactory((List<Styleable>) map.get(VBoxFactory.CHILDREN)));
        instantiators.put(WebView.class, map -> new WebViewFactory());
        instantiators.put(Popup.class, map -> new PopupFactory(
                (Node) map.get(PopupFactory.PARAM_CONTENT),
                (List<Button>) map.get(PopupFactory.PARAM_BUTTONS)));
        instantiators.put(MenuItem.class, map -> new MenuItemFactory(
                (String) map.get(MenuItemFactory.PARAM_NAME),
                (EventHandler<ActionEvent>) map.get(MenuItemFactory.PARAM_ACTION)));

    }

    @Override
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, Map options) throws CoreException {
        Function<Map, StyleableFactory> factoryFunction = instantiators.computeIfAbsent(klass, x -> null);
        StyleableFactory<T> nodeFactory = factoryFunction.apply(options);
        return nodeFactory.makeNode(locationContext);
    }

    @Override
    public int getPreference() {
        return 0;
    }
}
