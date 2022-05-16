package it.core.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.controls.service.DefaultNodeInstantiationService;
import com.legyver.fenxlib.core.scene.controls.factory.*;
import com.legyver.fenxlib.core.util.MapBuilder;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Styleable;
import javafx.scene.control.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class DefaultNodeInstantiationServiceTest extends FenxlibTest {
    private static final DefaultNodeInstantiationService service = new DefaultNodeInstantiationService();

    @Test
    public void instantiateAccordion() throws Exception {
        test(Accordion.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(Accordion.class);
        });
    }

    @Test
    public void instantiateButtonBar() throws Exception {
        test(ButtonBar.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(ButtonBar.class);
        });
    }

    @Test
    public void instantiateButton() throws Exception {
        test(Button.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(Button.class);
            assertThat(instantiated.getText()).isNull();
            assertThat(instantiated.isCancelButton()).isFalse();
            assertThat(instantiated.isDefaultButton()).isFalse();
        });
        test(Button.class, MapBuilder.of(ButtonFactory.CONSTRUCTOR_PARAM_TEXT, "test")
                .with(ButtonFactory.CONSTRUCTOR_PARAM_IS_CANCEL_BUTTON, true)
                .build(), instantiated -> {
            assertThat(instantiated).isInstanceOfAny(Button.class);
            assertThat(instantiated.getText()).isEqualTo("test");
            assertThat(instantiated.isCancelButton()).isTrue();
            assertThat(instantiated.isDefaultButton()).isFalse();
        });
        test(Button.class, MapBuilder.of(ButtonFactory.CONSTRUCTOR_PARAM_IS_DEFAULT_BUTTON, true)
                .build(), instantiated -> {
            assertThat(instantiated).isInstanceOfAny(Button.class);
            assertThat(instantiated.getText()).isNull();
            assertThat(instantiated.isCancelButton()).isFalse();
            assertThat(instantiated.isDefaultButton()).isTrue();
        });
    }

    @Test
    public void instantiateCheckBox() throws Exception {
        test(CheckBox.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(CheckBox.class);
            assertThat(instantiated.getText()).isNull();
            assertThat(instantiated.isSelected()).isFalse();
        });
        test(CheckBox.class, MapBuilder.of(CheckBoxFactory.CONSTRUCTOR_PARAM_TEXT, "test")
                .with(CheckBoxFactory.CONSTRUCTOR_PARAM_IS_SELECTED, true)
                .build(), instantiated -> {
            assertThat(instantiated.getText()).isEqualTo("test");
            assertThat(instantiated.isSelected()).isTrue();
        });
    }

    @Test
    public void instantiateCheckMenuItem() throws Exception {
        test(CheckMenuItem.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(CheckMenuItem.class);
            assertThat(instantiated.getText()).isNull();
            assertThat(instantiated.isSelected()).isFalse();
            assertThat(instantiated.isDisable()).isFalse();
        });
        test(CheckMenuItem.class, MapBuilder.of(CheckMenuItemFactory.CONSTRUCTOR_PARAM_TEXT, "test")
                .with(CheckMenuItemFactory.CONSTRUCTOR_PARAM_IS_SELECTED, true)
                .build(), instantiated -> {
            assertThat(instantiated.getText()).isEqualTo("test");
            assertThat(instantiated.isSelected()).isTrue();
            assertThat(instantiated.isDisable()).isFalse();
        });
        test(CheckMenuItem.class, MapBuilder.of(CheckMenuItemFactory.CONSTRUCTOR_PARAM_IS_DISABLED, true)
                .build(), instantiated -> {
            assertThat(instantiated.isDisable()).isTrue();
        });
    }

    @Test
    public void instantiateChoiceBox() throws Exception {
        test(ChoiceBox.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(ChoiceBox.class);
            assertThat(instantiated.getItems()).isEmpty();
        });
        List<String> itemList = Arrays.asList("One", "Two");
        test(ChoiceBox.class, Map.of(ChoiceBoxFactory.CONSTRUCTOR_PARAM_ITEMS, itemList), instantiated -> {
            assertThat(instantiated.getItems()).containsExactlyElementsOf(itemList);
        });
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("One");
        test(ChoiceBox.class, Map.of(ChoiceBoxFactory.CONSTRUCTOR_PARAM_ITEMS, observableList), instantiated -> {
            assertThat(instantiated.getItems()).containsExactly("One");
            observableList.add("Two");
            assertThat(instantiated.getItems()).containsExactly("One", "Two");
        });
    }

    @Test
    public void instantiateColorPicker() throws Exception {
        test(ColorPicker.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(ColorPicker.class);
        });
    }

   @Test
    public void instantiateComboBox() throws Exception {
        test(ComboBox.class, null, instantiated -> {
            assertThat(instantiated).isInstanceOfAny(ComboBox.class);
        });
       List<String> itemList = Arrays.asList("One", "Two");
       test(ComboBox.class, Map.of(ComboBoxFactory.CONSTRUCTOR_PARAM_ITEMS, itemList), instantiated -> {
           assertThat(instantiated.getItems()).containsExactlyElementsOf(itemList);
       });
       ObservableList<String> observableList = FXCollections.observableArrayList();
       observableList.add("One");
       test(ComboBox.class, Map.of(ComboBoxFactory.CONSTRUCTOR_PARAM_ITEMS, observableList), instantiated -> {
           assertThat(instantiated.getItems()).containsExactly("One");
           observableList.add("Two");
           assertThat(instantiated.getItems()).containsExactly("One", "Two");
       });
    }


    @Test
    public void instantiateLabel() throws Exception {
        test(Label.class, null, label -> {
            assertThat(label.getText()).isNullOrEmpty();
        });
        test(Label.class, Map.of(LabelFactory.DEFAULT_TEXT, "test"), label -> {
            assertThat(label.getText()).isEqualTo("test");
        });
        StringProperty bindTo = new SimpleStringProperty("test");
        test(Label.class, Map.of(LabelFactory.BIND_TO, bindTo), label -> {
            assertThat(label.getText()).isEqualTo("test");
            bindTo.set("test2");
            assertThat(label.getText()).isEqualTo("test2");
            try {
                label.setText("X");
                fail("Expected that an exception be thrown because a bound value cannot be set");
            } catch (Exception exception) {
                //expected
            }
        });
    }

    private static <T extends Styleable> void test(Class<T> klassToInstantiate, Map options, Consumer<T> asserter) throws CoreException {
        T instantiated = service.instantiate(klassToInstantiate, new DefaultLocationContext("Test"), options);
        asserter.accept(instantiated);
    }
}
