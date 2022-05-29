package it.core.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.controls.service.DefaultNodeInstantiationService;
import com.legyver.fenxlib.core.scene.controls.factory.*;
import com.legyver.fenxlib.core.util.map.MapBuilder;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Styleable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Disabled;
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

    @Test
    public void instantiateListView() throws Exception {
        test(ListView.class, null, listView -> {
            assertThat(listView.getItems()).isEmpty();
            assertThat(listView.isEditable()).isFalse();
        });
        test(ListView.class, Map.of(ListViewFactory.IS_EDITABLE, true), listView -> {
            assertThat(listView.getItems()).isEmpty();
            assertThat(listView.isEditable()).isTrue();
        });
    }

    @Test
    public void instantiatePagination() throws Exception {
        test(Pagination.class, null, pagination -> {
            assertThat(pagination).isNotNull();
        });
    }

    @Test
    public void instantiatePasswordField() throws Exception {
        test(PasswordField.class, null, passwordField -> {
            assertThat(passwordField).isNotNull();
        });
    }

    @Test
    public void instantiateProgressBar() throws Exception {
        test(ProgressBar.class, null, progressBar -> {
            assertThat(progressBar).isNotNull();
        });
    }

    @Test
    public void instantiateProgressIndicator() throws Exception {
        test(ProgressIndicator.class, null, progressIndicator -> {
            assertThat(progressIndicator).isNotNull();
        });
    }

    @Test
    public void instantiateRadioButton() throws Exception {
        test(RadioButton.class, null, radioButton -> {
            assertThat(radioButton).isNotNull();
        });
    }

    @Test
    public void instantiateRadioMenuItem() throws Exception {
        test(RadioMenuItem.class, null, radioMenuItem -> {
            assertThat(radioMenuItem).isNotNull();
        });
    }

    @Test
    public void instantiateScrollBar() throws Exception {
        test(ScrollBar.class, null, scrollBar -> {
            assertThat(scrollBar).isNotNull();
        });
    }

   @Test
    public void instantiateScrollPane() throws Exception {
        test(ScrollPane.class, null, scrollPane -> {
            assertThat(scrollPane).isNotNull();
        });
    }

    @Test
    public void instantiateSeparator() throws Exception {
        test(Separator.class, null, separator -> {
            assertThat(separator).isNotNull();
        });
    }

   @Test
    public void instantiateSeparatorMenuItem() throws Exception {
        test(SeparatorMenuItem.class, null, separator -> {
            assertThat(separator).isNotNull();
        });
    }

    @Test
    public void instantiateSpinner() throws Exception {
        test(Spinner.class, null, spinner -> {
            assertThat(spinner).isNotNull();
        });
    }

    @Test
    public void instantiateTab() throws Exception {
        test(Tab.class, null, tab -> {
            assertThat(tab).isNotNull();
        });
    }

    @Test
    public void instantiateTextArea() throws Exception {
        test(TextArea.class, null, textField -> {
            assertThat(textField).isNotNull();
        });
        test(TextArea.class, Map.of(TextAreaFactory.DEFAULT_TEXT, "test"), textArea -> {
            assertThat(textArea.getText()).isEqualTo("test");
        });
        StringProperty bindTo = new SimpleStringProperty("test");
        test(TextArea.class, Map.of(TextAreaFactory.BIND_TO, bindTo), textArea -> {
            assertThat(textArea.getText()).isEqualTo("test");
            bindTo.set("test2");
            assertThat(textArea.getText()).isEqualTo("test2");
            textArea.setText("test3");
            assertThat(bindTo.get()).isEqualTo("test3");
        });
    }

    @Test
    public void instantiateTextField() throws Exception {
        test(TextField.class, null, textField -> {
            assertThat(textField).isNotNull();
        });
        test(TextField.class, Map.of(TextFieldFactory.DEFAULT_TEXT, "test"), textField -> {
            assertThat(textField.getText()).isEqualTo("test");
        });
        StringProperty bindTo = new SimpleStringProperty("test");
        test(TextField.class, Map.of(TextFieldFactory.BIND_TO, bindTo), textField -> {
            assertThat(textField.getText()).isEqualTo("test");
            bindTo.set("test2");
            assertThat(textField.getText()).isEqualTo("test2");
            textField.setText("test3");
            assertThat(bindTo.get()).isEqualTo("test3");
        });
    }

    @Test
    public void instantiateToggleButton() throws Exception {
        test(ToggleButton.class, null, toggleButton -> {
            assertThat(toggleButton).isNotNull();
            assertThat(toggleButton.isSelected()).isFalse();
        });
        test(ToggleButton.class, Map.of(ToggleButtonFactory.DEFAULT_TEXT, "test"), toggleButton -> {
            assertThat(toggleButton.getText()).isEqualTo("test");
        });
        test(ToggleButton.class, Map.of(ToggleButtonFactory.SELECTED, true), toggleButton -> {
            assertThat(toggleButton.isSelected()).isTrue();
        });
        test(ToggleButton.class, Map.of(ToggleButtonFactory.SELECTED, false), toggleButton -> {
            assertThat(toggleButton.isSelected()).isFalse();
        });
    }

    @Test
    public void instantiateToolbar() throws Exception {
        test(ToolBar.class, null, toolBar -> {
            assertThat(toolBar).isNotNull();
        });
    }

    @Test
    public void instantiateAnchorPane() throws Exception {
        test(AnchorPane.class, null, anchorPane -> {
            assertThat(anchorPane).isNotNull();
        });
    }

    @Test
    public void instantiateBorderPane() throws Exception {
        test(BorderPane.class, null, borderPane -> {
            assertThat(borderPane).isNotNull();
        });
    }

    @Test
    public void instantiateFlowPane() throws Exception {
        test(FlowPane.class, null, flowPane -> {
            assertThat(flowPane).isNotNull();
        });
    }

    @Test
    public void instantiateGridPane() throws Exception {
        test(GridPane.class, null, gridPane -> {
            assertThat(gridPane).isNotNull();
        });
    }

    @Test
    public void instantiateHBox() throws Exception {
        test(HBox.class, null, hBox -> {
            assertThat(hBox).isNotNull();
        });
    }

    @Test
    public void instantiatePane() throws Exception {
        test(Pane.class, null, pane -> {
            assertThat(pane).isNotNull();
        });
    }

    @Test
    public void instantiateRegion() throws Exception {
        test(Region.class, null, region -> {
            assertThat(region).isNotNull();
        });
    }

    @Test
    public void instantiateStackPane() throws Exception {
        test(StackPane.class, null, stackPane -> {
            assertThat(stackPane).isNotNull();
        });
    }

    @Test
    public void instantiateTilePane() throws Exception {
        test(TilePane.class, null, tilePane -> {
            assertThat(tilePane).isNotNull();
        });
    }

  @Test
    public void instantiateTitledPane() throws Exception {
        test(TitledPane.class, null, titledPane -> {
            assertThat(titledPane).isNotNull();
        });
        test(TitledPane.class, Map.of(TitledPaneFactory.TITLE, "test"), titledPane -> {
            assertThat(titledPane.getText()).isEqualTo("test");
        });
        Pane pane = new Pane();
        test(TitledPane.class, Map.of(TitledPaneFactory.CONTENT, pane), titledPane -> {
            assertThat(titledPane.getContent()).isEqualTo(pane);
        });
    }

    @Test
    public void instantiateTabPane() throws Exception {
        test(TabPane.class, null, tabPane -> {
            assertThat(tabPane).isNotNull();
        });
    }

    @Test
    public void instantiateVBox() throws Exception {
        test(VBox.class, null, vBox -> {
            assertThat(vBox).isNotNull();
        });
    }

    @Test
    public void instantiateText() throws Exception {
        test(Text.class, null, text -> {
            assertThat(text).isNotNull();
        });
    }

    @Test
    public void instantiateTextFlow() throws Exception {
        test(TextFlow.class, null, textFlow -> {
            assertThat(textFlow).isNotNull();
        });
    }

    /**
     * Currently disabled for the following error
     * Not on FX application thread; currentThread = Test worker
     * java.lang.IllegalStateException: Not on FX application thread; currentThread = Test worker
     * 	at com.sun.javafx.tk.Toolkit.checkFxUserThread(Toolkit.java:295)
     * 	at com.sun.javafx.tk.quantum.QuantumToolkit.checkFxUserThread(QuantumToolkit.java:458)
     * 	at javafx.scene.web.WebEngine.checkThread(WebEngine.java:1220)
     * 	at javafx.scene.web.WebEngine.<init>(WebEngine.java:852)
     * 	at javafx.scene.web.WebEngine.<init>(WebEngine.java:839)
     * 	at javafx.scene.web.WebView.<init>(WebView.java:271)
     */
    @Disabled
    @Test
    public void instantiateWebView() throws Exception {
        test(WebView.class, null, webView -> {
            assertThat(webView).isNotNull();
        });
    }

    private static <T extends Styleable> void test(Class<T> klassToInstantiate, Map options, Consumer<T> asserter) throws CoreException {
        T instantiated = service.instantiate(klassToInstantiate, new DefaultLocationContext("Test"), options);
        asserter.accept(instantiated);
    }
}
