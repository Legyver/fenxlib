package com.legyver.fenxlib.extensions.materialfx;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.factory.ControlFactory;
import com.legyver.fenxlib.api.controls.options.ControlOptions;
import com.legyver.fenxlib.api.controls.service.NodeInstantiationService;
import com.legyver.fenxlib.extensions.materialfx.controls.factory.*;
import io.github.palexdev.materialfx.controls.*;
import javafx.css.Styleable;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Instantiation service MaterialFX controls
 */
public class MaterialFXNodeInstantiationService implements NodeInstantiationService {
    private static final Map<Class<? extends Styleable>, Supplier<ControlFactory>> instantiators = new HashMap<>();

    static {
        putAndAlias(MFXButton.class, Button.class, () -> new MFXButtonFactory());
        putAndAlias(MFXCheckbox.class, CheckBox.class, () -> new MFXCheckBoxFactory());
        //MFXComboBox does not extend ComboBox, so don't replace original ComboBox factory
        instantiators.put(MFXComboBox.class, () -> new MFXComboBoxFactory());
        instantiators.put(MFXFilterComboBox.class, () -> new MFXFilterComboBoxFactory());
        //MFXDatePicker does not extend DatePicker, so don't replace original DatePicker factory
        instantiators.put(MFXDatePicker.class, () -> new MFXDatePickerFactory());
        //MFXListView does not extend ListView, so don't replace original ListView factory
        instantiators.put(MFXListView.class, () -> new MFXListViewFactory());
        //MFXPagination does not extend Pagination, so don't replace original Pagination factory
        instantiators.put(MFXPagination.class, () -> new MFXPaginationFactory());
        //MFXPasswordField does not extend PasswordField, so don't replace original PasswordField factory
        instantiators.put(MFXPasswordField.class, () -> new MFXPasswordFieldFactory());
        putAndAlias(MFXProgressBar.class, ProgressBar.class, () -> new MFXProgressBarFactory());
        putAndAlias(MFXRadioButton.class, RadioButton.class, () -> new MFXRadioButtonFactory());
        putAndAlias(MFXScrollPane.class, ScrollPane.class, () -> new MFXScrollPaneFactory());
        //MFXSlider does not extend Slider, so don't replace original Slider factory
        instantiators.put(MFXSlider.class, () -> new MFXSliderFactory());
        //MFXSpinner does not extend Spinner, so don't replace original Spinner factory
        instantiators.put(MFXSpinner.class, () -> new MFXSpinnerFactory());
        putAndAlias(MFXTextField.class, TextField.class, () -> new MFXTextFieldFactory());
        //MFXToggleButton does not extend ToggleButton, so don't replace original ToggleButton factory
        instantiators.put(MFXToggleButton.class, () -> new MFXToggleButtonFactory());
        //MFXTooltip does not extend Tooltip, so don't replace original Tooltip factory
        instantiators.put(MFXTooltip.class, () -> new MFXTooltipFactory());

        //materialfx specials
        instantiators.put(MFXFilterPane.class, () -> new MFXFilterPaneFactory());
        instantiators.put(MFXIconWrapper.class, () -> new MFXIconWrapperFactory());
        instantiators.put(MFXMagnifierPane.class, () -> new MFXMagnifierPaneFactory());
        instantiators.put(MFXTableView.class, () -> new MFXTableViewFactory());
        instantiators.put(MFXPaginatedTableView.class, () -> new MFXPaginatedTableViewFactory());
        instantiators.put(MFXProgressSpinner.class, () -> new MFXProgressSpinnerFactory());
        instantiators.put(MFXStepper.class, () -> new MFXStepperFactory());
        instantiators.put(MFXTableColumn.class, () -> new MFXTableColumnFactory());
        instantiators.put(MFXTableRow.class, () -> new MFXTableRowFactory());
        instantiators.put(MFXTreeItem.class, () -> new MFXTreeItemFactory());
        instantiators.put(MFXTreeView.class, () -> new MFXTreeViewFactory());
    }

    private static void putAndAlias(Class klass, Class aliasClass, Supplier<ControlFactory> instantiator) {
        instantiators.put(klass, instantiator);
        instantiators.put(aliasClass, instantiator);
    }

    @Override
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, ControlOptions<T> options) throws CoreException {
        Supplier<ControlFactory> factoryFunction = instantiators.get(klass);
        if (factoryFunction != null) {
            ControlFactory<T, ControlOptions<T>> nodeFactory = factoryFunction.get();
            return nodeFactory.makeNode(locationContext, options);
        }
        return null;
    }

    @Override
    public int getPreference() {
        return -100;
    }
}
