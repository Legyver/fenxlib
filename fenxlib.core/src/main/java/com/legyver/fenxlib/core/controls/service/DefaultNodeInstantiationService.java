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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
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

    private static final Map<Class, Function<Map, StyleableFactory>> instantiators = new HashMap<>();

    static {
        //javafx.scene.control
        instantiators.put(Accordion.class, map -> new AccordionFactory());
        instantiators.put(ButtonBar.class, map -> new ButtonBarFactory());
        instantiators.put(Button.class, map -> new ButtonFactory(
                (String) map.get(ButtonFactory.CONSTRUCTOR_PARAM_TEXT),
                (Boolean) map.get(ButtonFactory.CONSTRUCTOR_PARAM_IS_CANCEL_BUTTON),
                (Boolean) map.get(ButtonFactory.CONSTRUCTOR_PARAM_IS_DEFAULT_BUTTON)
        ));
        instantiators.put(CheckBox.class, map -> new CheckBoxFactory(
                (String) map.get(CheckBoxFactory.CONSTRUCTOR_PARAM_TEXT),
                (Boolean) map.get(CheckBoxFactory.CONSTRUCTOR_PARAM_IS_SELECTED)
        ));
        instantiators.put(CheckMenuItem.class, map -> new CheckMenuItemFactory(
                (String) map.get(CheckMenuItemFactory.CONSTRUCTOR_PARAM_TEXT),
                (Boolean) map.get(CheckMenuItemFactory.CONSTRUCTOR_PARAM_IS_SELECTED),
                (Boolean) map.get(CheckMenuItemFactory.CONSTRUCTOR_PARAM_IS_DISABLED)
        ));
        instantiators.put(ChoiceBox.class, map -> new ChoiceBoxFactory((List) map.get(ChoiceBoxFactory.CONSTRUCTOR_PARAM_ITEMS)));
        instantiators.put(ColorPicker.class, map -> new ColorPickerFactory());
        instantiators.put(ComboBox.class, map -> new ComboBoxFactory((List) map.get(ComboBoxFactory.CONSTRUCTOR_PARAM_ITEMS)));
        instantiators.put(ContextMenu.class, map -> new ContextMenuFactory());
        instantiators.put(CustomMenuItem.class, map -> new CustomMenuItemFactory());
        instantiators.put(DateCell.class, map -> new DateCellFactory());
        instantiators.put(Hyperlink.class, map -> new SystemBrowserHyperlinkFactory((String) map.get(SystemBrowserHyperlinkFactory.URL), null));
        instantiators.put(Label.class, map -> new LabelFactory(
                (StringProperty) map.get(LabelFactory.BIND_TO),
                (String) map.get(LabelFactory.DEFAULT_TEXT)
        ));
        instantiators.put(ListView.class, map -> new ListViewFactory((Boolean) map.get(ListViewFactory.IS_EDITABLE)));
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
        instantiators.put(TextArea.class, map -> new TextAreaFactory());
        instantiators.put(TextField.class, map -> new TextFieldFactory(
                (StringProperty) map.get(TextFieldFactory.BIND_TO),
                (String) map.get(TextFieldFactory.DEFAULT_TEXT),
                (BooleanProperty) map.get(TextFieldFactory.BIND_TO_EDITABLE),
                (Boolean) map.get(TextFieldFactory.IS_EDITABLE)
        ));
        instantiators.put(ToggleButton.class, map -> new ToggleButtonFactory());
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
                (String) map.get(TitledPaneFactory.CONTENT),
                (Pane) map.get(TitledPaneFactory.CONTENT)
        ));
        instantiators.put(TabPane.class, map -> new TabPaneFactory((List<? extends Tab>) map.get(TabPaneFactory.TABS)));
        instantiators.put(VBox.class, map -> new VBoxFactory());
        instantiators.put(StackPane.class, map -> new StackPaneFactory());

       //javafx.scene.text
        instantiators.put(Text.class, map -> new TextFactory((String) map.get(TextFactory.TEXT)));
        instantiators.put(TextFlow.class, map -> new TextFlowFactory((List<Styleable>) map.get(TextFlowFactory.CHILDREN)));

        //javafx.scene.web
        instantiators.put(WebView.class, map -> new WebViewFactory());

        //custom
        instantiators.put(Popup.class, map -> new PopupFactory(
                (Node) map.get(PopupFactory.PARAM_CONTENT),
                (List<Button>) map.get(PopupFactory.PARAM_BUTTONS)));
        instantiators.put(MenuItem.class, map -> new MenuItemFactory(
                (String) map.get(MenuItemFactory.PARAM_NAME),
                (EventHandler<ActionEvent>) map.get(MenuItemFactory.PARAM_ACTION)));

    }

    @Override
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, Map options) throws CoreException {
        Function<Map, StyleableFactory> factoryFunction = instantiators.get(klass);
        if (factoryFunction == null) {
            throw new CoreException("No factory registered for class: " + klass);
        } else {
            StyleableFactory<T> nodeFactory = factoryFunction.apply(options == null ? new HashMap() : options);
            return nodeFactory.makeNode(locationContext);
        }
    }

    @Override
    public int getPreference() {
        return 0;
    }
}
