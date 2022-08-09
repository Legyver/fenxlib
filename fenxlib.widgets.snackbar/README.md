# FileTree
## Usage
### Dependency
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.widgets.snackbar', version: '3.0.0-beta.1'
```
## Code
```java
 Supplier<StackPane> centerContentReference = () -> {
    Optional<StackPane> center = new ComponentQuery.QueryBuilder()
    .inRegion(BorderPaneInitializationOptions.REGION_CENTER)
    .type(StackPane.class).execute();
    return center.get();
};

AlertGeneratingForm alertGeneratingForm = new AlertGeneratingForm();
alertGeneratingForm.valueProperty().addListener((observable, oldValue, newValue) -> {
    if (NumberUtils.isDigits(newValue)) {
        //show info message for 1 second
        ApplicationContext.infoAlert(centerContentReference.get(), "OK", "Number entered: " + newValue, 1000L);
    } else if (NumberUtils.isParsable(newValue)) {
        //show warning message for 2 seconds
        ApplicationContext.warningAlert(centerContentReference.get(), "Warning", "Number contains a decimal: " + newValue, 2000L);
    } else {
        //show error message.  No timeout.  Message closes when user clicks away.
        ApplicationContext.errorAlert(centerContentReference.get(), "Bad value", "Value is not a number: " + newValue);
    }
});
```
for complete example checkout fenxlib-samples-alert
