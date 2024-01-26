# fenxlib materialfx
An extension to supply MaterialFX wigets

## Usage
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.extensions.materialfx', version: '3.0.2'
```

## Notes
Where possible a one-to-one swap is performed to substitute the MaterialFX widget for the plain JavaFX widget.
However, where MaterialFX widgets do not extend a plain JavaFX widget, this is not possible.

The following or do not extend (or do not have) a JavaFX counterpart
- MFXComboBox
- MFXDatePicker
- MFXFilterPane
- MFXIconWrapper
- MFXListView
- MFXMagnifierPane
- MFXPagination
- MFXPasswordField
- MFXProgressSpinner
- MFXSlider
- MFXSpinner
- MFXStepper
- MFXStepperToggle
- MFXTableColumn
- MFXTableRow
- MFXTableView
- MFXToggleButton
- MFXTooltip
- MFXTreeItem
- MFXTreeView

## Examples
```java
public class Example {
    public void showExamples() {
        //if fenxlib.extensions.materialfx is on the classpath, this will instantiate a MFXButton
        Button button = ControlsFactory.make(Button.class);
        //This will do the same
        MFXButton mfxButton = ControlsFactory.make(MFXButton.class);
        
        //Since MFXComboBox does not extend ComboBox, this will return a ComboBox
        ComboBox comboBox = ControlsFactory.make(ComboBox.class);
        //if you want a MFXComboBox, you need to be explicit 
        MFXComboBox mfxComboBox = ControlsFactory.make(MFXComboBox.class);
        
    }
}
```