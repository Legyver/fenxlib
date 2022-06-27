package com.legyver.fenxlib.core.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.ControlFactory;
import com.legyver.fenxlib.core.controls.factory.SystemBrowserHyperlinkFactory;
import com.legyver.fenxlib.core.controls.options.ControlOptions;
import com.legyver.fenxlib.core.menu.factory.MenuFactory;
import com.legyver.fenxlib.core.menu.factory.MenuItemFactory;
import com.legyver.fenxlib.core.scene.controls.factory.*;
import com.legyver.fenxlib.core.scene.layout.factory.*;
import com.legyver.fenxlib.core.scene.text.factory.TextFactory;
import com.legyver.fenxlib.core.scene.text.factory.TextFlowFactory;
import com.legyver.fenxlib.core.scene.web.factory.WebViewFactory;
import javafx.css.Styleable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Instantiate default JavaFX controls with raw JavaFX implementation
 */
public class DefaultNodeInstantiationService implements NodeInstantiationService {

    private static final Map<Class, Supplier<ControlFactory>> instantiators = new HashMap<>();

    static {
        //javafx.scene.control
        instantiators.put(Accordion.class, () -> new AccordionFactory());
        instantiators.put(ButtonBar.class, () -> new ButtonBarFactory());
        instantiators.put(Button.class, () -> new ButtonFactory());
        instantiators.put(CheckBox.class, () -> new CheckBoxFactory());
        instantiators.put(CheckMenuItem.class, () -> new CheckMenuItemFactory());
        instantiators.put(ChoiceBox.class, () -> new ChoiceBoxFactory());
        instantiators.put(ColorPicker.class, () -> new ColorPickerFactory());
        instantiators.put(ComboBox.class, () -> new ComboBoxFactory());
        instantiators.put(ContextMenu.class, () -> new ContextMenuFactory());
        instantiators.put(CustomMenuItem.class, () -> new CustomMenuItemFactory());
        instantiators.put(DateCell.class, () -> new DateCellFactory());
        instantiators.put(DatePicker.class, () -> new DatePickerFactory());
        instantiators.put(Hyperlink.class, () -> new SystemBrowserHyperlinkFactory());
        instantiators.put(Label.class, () -> new LabelFactory());
        instantiators.put(ListView.class, () -> new ListViewFactory());
        instantiators.put(Menu.class, () -> new MenuFactory());
        instantiators.put(MenuItem.class, () -> new MenuItemFactory());
        instantiators.put(Pagination.class, () -> new PaginationFactory());
        instantiators.put(PasswordField.class, () -> new PasswordFieldFactory());
        instantiators.put(ProgressBar.class, () -> new ProgressBarFactory());
        instantiators.put(ProgressIndicator.class, () -> new ProgressIndicatorFactory());
        instantiators.put(RadioButton.class, () -> new RadioButtonFactory());
        instantiators.put(RadioMenuItem.class, () -> new RadioMenuItemFactory());
        instantiators.put(ScrollBar.class, () -> new ScrollBarFactory());
        instantiators.put(ScrollPane.class, () -> new ScrollPaneFactory());
        instantiators.put(Separator.class, () -> new SeparatorFactory());
        instantiators.put(SeparatorMenuItem.class, () -> new SeparatorMenuItemFactory());
        instantiators.put(Slider.class, () -> new SliderFactory());
        instantiators.put(Spinner.class, () -> new SpinnerFactory());
        instantiators.put(SplitPane.class, () -> new SplitPaneFactory());
        instantiators.put(Tab.class, () -> new TabFactory());
        instantiators.put(TabPane.class, () -> new TabPaneFactory());
        instantiators.put(TextArea.class, () -> new TextAreaFactory());
        instantiators.put(TextField.class, () -> new TextFieldFactory());
        instantiators.put(TitledPane.class, () -> new TitledPaneFactory());
        instantiators.put(ToggleButton.class, () -> new ToggleButtonFactory());
        instantiators.put(ToolBar.class, () -> new ToolBarFactory());
        instantiators.put(TreeItem.class, () -> new TreeItemFactory());
        instantiators.put(TreeView.class, () -> new TreeViewFactory());

        //  javafx.scene.layout
        instantiators.put(AnchorPane.class, () -> new AnchorPaneFactory());
        instantiators.put(BorderPane.class, () -> new BorderPaneFactory());
        instantiators.put(FlowPane.class, () -> new FlowPaneFactory());
        instantiators.put(GridPane.class, () -> new GridPaneFactory());
        instantiators.put(HBox.class, () -> new HBoxFactory());
        instantiators.put(Pane.class, () -> new PaneFactory());
        instantiators.put(Region.class, () -> new RegionFactory());
        instantiators.put(StackPane.class, () -> new StackPaneFactory());
        instantiators.put(TilePane.class, () -> new TilePaneFactory());
        instantiators.put(VBox.class, () -> new VBoxFactory());

       //javafx.scene.text
        instantiators.put(Text.class, () -> new TextFactory());
        instantiators.put(TextFlow.class, () -> new TextFlowFactory());

        //javafx.scene.web
        instantiators.put(WebView.class, () -> new WebViewFactory());

        //custom
//        instantiators.put(Popup.class, () -> new PopupFactory());

    }

    @Override
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, ControlOptions<T> options) throws CoreException {
        Supplier<ControlFactory> factoryFunction = instantiators.get(klass);
        if (factoryFunction == null) {
            throw new CoreException("No factory registered for class: " + klass);
        } else {
            ControlFactory<T, ControlOptions<T>> controlFactory = factoryFunction.get();
            if (locationContext == null && controlFactory.requiresLocationContext()) {
                locationContext = new DefaultLocationContext("");
            }
            if (options == null) {
                //this will instantiate a new Options of the correct class, so we avoid NPEs
                return controlFactory.makeNode(locationContext);
            }
            return controlFactory.makeNode(locationContext, options);
        }
    }

    @Override
    public int getPreference() {
        return 0;
    }
}
