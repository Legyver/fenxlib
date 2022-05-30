package com.legyver.fenxlib.extensions.materialfx;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.controls.service.NodeInstantiationService;
import com.legyver.fenxlib.core.util.map.FenxlibTypedMapAdapter;
import com.legyver.fenxlib.extensions.materialfx.controls.factory.*;
import io.github.palexdev.materialfx.controls.*;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Instantiation service MaterialFX controls
 */
public class MaterialFXNodeInstantiationService implements NodeInstantiationService {
    private static final Map<Class, Function<FenxlibTypedMapAdapter, StyleableFactory>> instantiators = new HashMap<>();

    static {
        putAndAlias(MFXButton.class, Button.class, map -> new MFXButtonFactory(
                map.getString(MFXButtonFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getBoolean(MFXButtonFactory.CONSTRUCTOR_PARAM_IS_CANCEL_BUTTON),
                map.getBoolean(MFXButtonFactory.CONSTRUCTOR_PARAM_IS_DEFAULT_BUTTON)
        ));
        putAndAlias(MFXCheckbox.class, CheckBox.class, map -> new MFXCheckBoxFactory(
                map.getString(MFXCheckBoxFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getBoolean(MFXCheckBoxFactory.CONSTRUCTOR_PARAM_IS_SELECTED)
        ));
        //MFXComboBox does not extend ComboBox, so don't replace original ComboBox factory
        instantiators.put(MFXComboBox.class, map -> new MFXComboBoxFactory(
                map.get(MFXComboBoxFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)
        ));
        instantiators.put(MFXFilterComboBox.class, map -> new MFXFilterComboBoxFactory(
                map.get(MFXFilterComboBoxFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)
        ));
        //MFXDatePicker does not extend DatePicker, so don't replace original DatePicker factory
        instantiators.put(MFXDatePicker.class, map -> new MFXDatePickerFactory());
        //MFXListView does not extend ListView, so don't replace original ListView factory
        instantiators.put(MFXListView.class, map -> new MFXListViewFactory(
                map.get(MFXListViewFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)
        ));
        //MFXPagination does not extend Pagination, so don't replace original Pagination factory
        instantiators.put(MFXPagination.class, map -> new MFXPaginationFactory(
                map.getInteger(MFXPaginationFactory.CONSTRUCTOR_PARAM_MAX_PAGE),
                map.getInteger(MFXPaginationFactory.CONSTRUCTOR_PARAM_PAGES_TO_SHOW)
        ));
        //MFXPasswordField does not extend PasswordField, so don't replace original PasswordField factory
        instantiators.put(MFXPasswordField.class, map -> new MFXPasswordFieldFactory(
                map.getString(MFXPasswordFieldFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getString(MFXPasswordFieldFactory.CONSTRUCTOR_PARAM_PROMPT_TEXT),
                map.getString(MFXPasswordFieldFactory.CONSTRUCTOR_PARAM_FLOAT_TEXT)
        ));
        putAndAlias(MFXProgressBar.class, ProgressBar.class, map -> new MFXProgressBarFactory());
        putAndAlias(MFXRadioButton.class, RadioButton.class, map -> new MFXRadioButtonFactory());
        putAndAlias(MFXScrollPane.class, ScrollPane.class, map -> new MFXScrollPaneFactory());
        //MFXSlider does not extend Slider, so don't replace original Slider factory
        instantiators.put(MFXSlider.class, map -> new MFXSliderFactory(
                map.getDouble(MFXSliderFactory.CONSTRUCTOR_PARAM_MIN),
                map.getDouble(MFXSliderFactory.CONSTRUCTOR_PARAM_MAX),
                map.getDouble(MFXSliderFactory.CONSTRUCTOR_PARAM_INITIAL_VALUE)
        ));
        //MFXSpinner does not extend Spinner, so don't replace original Spinner factory
        instantiators.put(MFXSpinner.class, map -> new MFXSpinnerFactory());
        putAndAlias(MFXTextField.class, TextField.class, map -> new MFXTextFieldFactory(
                map.getStringProperty(MFXTextFieldFactory.BIND_TO),
                map.getString(MFXTextFieldFactory.DEFAULT_TEXT),
                map.getBooleanProperty(MFXTextFieldFactory.BIND_TO_EDITABLE),
                map.getBoolean(MFXTextFieldFactory.IS_EDITABLE)
        ));
        //MFXToggleButton does not extend ToggleButton, so don't replace original ToggleButton factory
        instantiators.put(MFXToggleButton.class, map -> new MFXToggleButtonFactory(
                map.getString(MFXToggleButtonFactory.CONSTRUCTOR_PARAM_TEXT),
                map.get(MFXToggleButtonFactory.CONSTRUCTOR_PARAM_GRAPHIC, Node.class)
        ));
        //MFXTooltip does not extend Tooltip, so don't replace original Tooltip factory
        instantiators.put(MFXTooltip.class, map -> new MFXTooltipFactory(
                map.get(MFXTooltipFactory.CONSTRUCTOR_PARAM_OWNER, Node.class),
                map.getString(MFXTooltipFactory.CONSTRUCTOR_PARAM_TEXT)
        ));

        //materialfx specials
        instantiators.put(MFXFilterPane.class, map -> new MFXFilterPaneFactory());
        instantiators.put(MFXIconWrapper.class, map -> new MFXIconWrapperFactory(
                map.getString(MFXIconWrapperFactory.CONSTRUCTOR_PARAM_ICON_DESCRIPTION),
                map.get(MFXIconWrapperFactory.CONSTRUCTOR_PARAM_ICON_NODE, Node.class),
                map.getDouble(MFXIconWrapperFactory.CONSTRUCTOR_PARAM_ICON_SIZE),
                map.getDouble(MFXIconWrapperFactory.CONSTRUCTOR_PARAM_WRAPPER_SIZE),
                map.get(MFXIconWrapperFactory.CONSTRUCTOR_PARAM_ICON_COLOR, Color.class)
        ));
        instantiators.put(MFXMagnifierPane.class, map -> new MFXMagnifierPaneFactory(
                map.get(MFXMagnifierPaneFactory.CONSTRUCTOR_PARAM_CONTENT, Node.class)
        ));
        instantiators.put(MFXTableView.class, map -> new MFXTableViewFactory(
                map.get(MFXTableViewFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)
        ));
        instantiators.put(MFXPaginatedTableView.class, map -> new MFXPaginatedTableViewFactory(
                map.get(MFXPaginatedTableViewFactory.CONSTRUCTOR_PARAM_ITEMS, List.class)
        ));
        instantiators.put(MFXProgressSpinner.class, map -> new MFXProgressSpinnerFactory());
        instantiators.put(MFXStepper.class, map -> new MFXStepperFactory());
        instantiators.put(MFXStepperToggle.class, map -> new MFXStepperToggleFactory(
                map.getString(MFXStepperToggleFactory.CONSTRUCTOR_PARAM_TEXT),
                map.get(MFXStepperToggleFactory.CONSTRUCTOR_PARAM_ICON, Node.class),
                map.get(MFXStepperToggleFactory.CONSTRUCTOR_PARAM_CONTENT, Node.class)
        ));
        instantiators.put(MFXTableColumn.class, map -> new MFXTableColumnFactory(
                map.getString(MFXTableColumnFactory.CONSTRUCTOR_PARAM_TEXT),
                map.getBoolean(MFXTableColumnFactory.CONSTRUCTOR_PARAM_RESIZABLE),
                map.get(MFXTableColumnFactory.CONSTRUCTOR_PARAM_COMPARATOR, Comparator.class)
        ));
        instantiators.put(MFXTableRow.class, map -> new MFXTableRowFactory(
                map.get(MFXTableRowFactory.CONSTRUCTOR_PARAM_TABLE_VIEW, MFXTableView.class),
                map.get(MFXTableRowFactory.CONSTRUCTOR_PARAM_DATA, Object.class)
        ));
        instantiators.put(MFXTreeItem.class, map -> new MFXTreeItemFactory(
                map.get(MFXTreeItemFactory.CONSTRUCTOR_PARAM_DATA, Object.class),
                map.get(MFXTreeItemFactory.CONSTRUCTOR_PARAM_CELL_FACTORY, Callback.class)
        ));
        instantiators.put(MFXTreeView.class, map -> new MFXTreeViewFactory(
                map.get(MFXTreeViewFactory.CONSTRUCTOR_PARAM_ROOT, MFXTreeItem.class)
        ));

    }

    private static void putAndAlias(Class klass, Class aliasClass, Function<FenxlibTypedMapAdapter, StyleableFactory> instantiator) {
        instantiators.put(klass, instantiator);
        instantiators.put(aliasClass, instantiator);
    }

    @Override
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, Map options) throws CoreException {
        Function<FenxlibTypedMapAdapter, StyleableFactory> factoryFunction = instantiators.get(klass);
        if (factoryFunction != null) {
            FenxlibTypedMapAdapter adapter = new FenxlibTypedMapAdapter(options == null ? new HashMap() : options);
            StyleableFactory<T> nodeFactory = factoryFunction.apply(adapter);
            return nodeFactory.makeNode(locationContext);
        }
        return null;
    }

    @Override
    public int getPreference() {
        return -100;
    }
}
