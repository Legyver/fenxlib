package com.legyver.fenxlib.core.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.*;
import com.legyver.fenxlib.core.controls.popup.Popup;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.menu.factory.MenuItemFactory;
import com.legyver.fenxlib.core.scene.controls.factory.*;
import com.legyver.fenxlib.core.scene.layout.factory.*;
import com.legyver.fenxlib.core.scene.text.factory.TextFactory;
import com.legyver.fenxlib.core.scene.text.factory.TextFlowFactory;
import com.legyver.fenxlib.core.scene.web.factory.WebViewFactory;
import com.legyver.fenxlib.core.util.map.FenxlibTypedMapAdapter;

import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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

    private static final Map<Class, Function<FenxlibTypedMapAdapter, StyleableFactory>> instantiators = new HashMap<>();

    static {
        //javafx.scene.control
        instantiators.put(Accordion.class, map -> new AccordionFactory());
        instantiators.put(ButtonBar.class, map -> new ButtonBarFactory());
        instantiators.put(Button.class, map -> new ButtonFactory<Button>(
                map.getString(ButtonFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getBoolean(ButtonFactory.CONSTRUCTOR_PARAM_IS_CANCEL_BUTTON),
                map.getBoolean(ButtonFactory.CONSTRUCTOR_PARAM_IS_DEFAULT_BUTTON)
        ));
        instantiators.put(CheckBox.class, map -> new CheckBoxFactory(
                map.getString(CheckBoxFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getBoolean(CheckBoxFactory.CONSTRUCTOR_PARAM_IS_SELECTED)
        ));
        instantiators.put(CheckMenuItem.class, map -> new CheckMenuItemFactory(
                map.getString(CheckMenuItemFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getBoolean(CheckMenuItemFactory.CONSTRUCTOR_PARAM_IS_SELECTED),
                map.getBoolean(CheckMenuItemFactory.CONSTRUCTOR_PARAM_IS_DISABLED)
        ));
        instantiators.put(ChoiceBox.class, map -> new ChoiceBoxFactory(map.get(ChoiceBoxFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)));
        instantiators.put(ColorPicker.class, map -> new ColorPickerFactory());
        instantiators.put(ComboBox.class, map -> new ComboBoxFactory(map.get(ComboBoxFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)));
        instantiators.put(ContextMenu.class, map -> new ContextMenuFactory());
        instantiators.put(CustomMenuItem.class, map -> new CustomMenuItemFactory());
        instantiators.put(DateCell.class, map -> new DateCellFactory());
        instantiators.put(Hyperlink.class, map -> new SystemBrowserHyperlinkFactory(map.getString(SystemBrowserHyperlinkFactory.URL), null));
        instantiators.put(Label.class, map -> new LabelFactory(
                map.getStringProperty(LabelFactory.BIND_TO),
                map.getString(LabelFactory.DEFAULT_TEXT)
        ));
        instantiators.put(ListView.class, map -> new ListViewFactory(map.getBoolean(ListViewFactory.IS_EDITABLE)));
        instantiators.put(Pagination.class, map -> new PaginationFactory());
        instantiators.put(PasswordField.class, map -> new PasswordFieldFactory());
        instantiators.put(ProgressBar.class, map -> new ProgressBarFactory());
        instantiators.put(ProgressIndicator.class, map -> new ProgressIndicatorFactory());
        instantiators.put(RadioButton.class, map -> new RadioButtonFactory());
        instantiators.put(RadioMenuItem.class, map -> new RadioMenuItemFactory());
        instantiators.put(ScrollBar.class, map -> new ScrollBarFactory());
        instantiators.put(ScrollPane.class, map -> new ScrollPaneFactory());
        instantiators.put(Separator.class, map -> new SeparatorFactory());
        instantiators.put(SeparatorMenuItem.class, map -> new SeparatorMenuItemFactory());
        instantiators.put(Spinner.class, map -> new SpinnerFactory());
        instantiators.put(Tab.class, map -> new TabFactory());
        instantiators.put(TextArea.class, map -> new TextAreaFactory(
                map.getStringProperty(TextAreaFactory.BIND_TO),
                map.getString(TextAreaFactory.DEFAULT_TEXT),
                map.getBooleanProperty(TextAreaFactory.BIND_TO_EDITABLE),
                map.getBoolean(TextAreaFactory.IS_EDITABLE)
        ));
        instantiators.put(TextField.class, map -> new TextFieldFactory(
                map.getStringProperty(TextFieldFactory.BIND_TO),
                map.getString(TextFieldFactory.DEFAULT_TEXT),
                map.getBooleanProperty(TextFieldFactory.BIND_TO_EDITABLE),
                map.getBoolean(TextFieldFactory.IS_EDITABLE)
        ));
        instantiators.put(ToggleButton.class, map -> new ToggleButtonFactory(
                map.getString(ToggleButtonFactory.DEFAULT_TEXT),
                map.getBoolean(ToggleButtonFactory.SELECTED)
        ));
        instantiators.put(ToolBar.class, map -> new ToolBarFactory());

        //  javafx.scene.layout
        instantiators.put(AnchorPane.class, map -> new AnchorPaneFactory());
        instantiators.put(BorderPane.class, map -> new BorderPaneFactory());
        instantiators.put(FlowPane.class, map -> new FlowPaneFactory());
        instantiators.put(GridPane.class, map -> new GridPaneFactory());
        instantiators.put(HBox.class, map -> new HBoxFactory());
        instantiators.put(Pane.class, map -> new PaneFactory());
        instantiators.put(Region.class, map -> new RegionFactory());
        instantiators.put(StackPane.class, map -> new StackPaneFactory());
        instantiators.put(TilePane.class, map -> new TilePaneFactory());

        instantiators.put(TitledPane.class, map -> new TitledPaneFactory(
                map.getString(TitledPaneFactory.TITLE),
                map.get(TitledPaneFactory.CONTENT, Pane.class)
        ));
        instantiators.put(TabPane.class, map -> new TabPaneFactory((List<? extends Tab>) map.get(TabPaneFactory.TABS, List.class)));
        instantiators.put(VBox.class, map -> new VBoxFactory());

       //javafx.scene.text
        instantiators.put(Text.class, map -> new TextFactory(map.getString(TextFactory.TEXT)));
        instantiators.put(TextFlow.class, map -> new TextFlowFactory((List<Styleable>) map.get(TextFlowFactory.CHILDREN, List.class)));

        //javafx.scene.web
        instantiators.put(WebView.class, map -> new WebViewFactory());

        //custom
        instantiators.put(Popup.class, map -> new PopupFactory(
                map.get(PopupFactory.PARAM_CONTENT, Node.class),
                (List<Button>) map.get(PopupFactory.PARAM_BUTTONS, List.class)));
        instantiators.put(MenuItem.class, map -> new MenuItemFactory(
                map.getString(MenuItemFactory.PARAM_NAME),
                (EventHandler<ActionEvent>) map.get(MenuItemFactory.PARAM_ACTION, EventHandler.class)));

    }

    @Override
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, Map options) throws CoreException {
        Function<FenxlibTypedMapAdapter, StyleableFactory> factoryFunction = instantiators.get(klass);
        if (factoryFunction == null) {
            throw new CoreException("No factory registered for class: " + klass);
        } else {
            FenxlibTypedMapAdapter adapter = new FenxlibTypedMapAdapter(options == null ? new HashMap() : options);
            StyleableFactory<T> nodeFactory = factoryFunction.apply(adapter);
            return nodeFactory.makeNode(locationContext);
        }
    }

    @Override
    public int getPreference() {
        return 0;
    }
}
