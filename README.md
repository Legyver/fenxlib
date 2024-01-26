# fenxlib
A UI framework for JavaFX.

Note: The distribution of this library is compiled on Java 17.

## A brief history
The roots of this library are in the intersection of two ideas:
- Java's build once, run anywhere paradigm does not really deliver any development cost in a world where Java typically powers web applications on servers that could be running any OS.  However, with desktop applications, the benefits could radically decrease development time. 
- It should be possible to write a class to represent a form as a bean that's fields are annotated with their representive widget.  For example:

```java
public class MyForm {
  
    @TextField
    String name;
  
    @SelectField({
            "Red",
            "Green",
            "Blue"
    })
    String choices;
}
```

The first take on this idea can be seen over on [fenxui](https://github.com/fenxui).  That however ran into problems with complexity.  As more and more functionality was added in, the more unmanageable it became.

The idea became to develop the wiring first.  This functionality could possibly be able to be leveraged in a new generation of the fenxui framework, thus the name ***fenx***(ui)***lib***

The key factor in the growing complexity was enabling various widgets to be able to retrieve the values of other widgets not necessarily in the same form.
In Fenxlib, the factories register the constructed controls with a registry.  These can then be queried

## Usage
Since version 2.0.0.0, this library has been made module-friendly, and hence the artifacts use dots as separators instead of traditional dashes.

The main functionality of this library is in the fenxlib.core module.

```gradle
implementation group: 'com.legyver', name: 'fenxlib.core', version: '3.0.2'
```

There are several extensions, widgets and skins available as well as independent dependencies

## The Basics
### Creating controls
Controls are created by using ControlsFactory.make(Class)

```java
TextField textField = ControlsFactory.make(TextField.class);
TextField textFieldCalledName = ControlsFactory.make(TextField.class, new TextFieldOptions()
        .name("name")
        .text("Alice"));

//Construct HBox[name="header", Label[name="currentUser"]]
LocationContext headerLocation = new DefaultLocationContext("header");
HBox hbox = ControlsFactory.make(HBox.class, headerLocation, new HBoxOptions());

Label label = ControlsFactory.make(Label.class, headerLocation, new LabelOptions()
        .name("currentUser"));
hbox.getChildren().add(label);
```
Constructing a control via ControlsFactory accomplishes several things
- Automatically registers the component with the component registry
- i18n translation of text/labels when using resource keys
- Allows for NodeInstantiationServices to be swapped out.  For example, we ship pure JavaFX controls by default. However, you can add the materialfx extension to have MaterialFX widgets used instead without having to change your code*.
  - (*) for controls that are one-to-one substitutions. This is not always possible as some MaterialFX controls do not extend the pure JavaFX class, or implement its interface.  

### Querying controls
Controls can be queried using the location and name/type of the control
```java
//Assume same HBox[name="header", Label[name="currentUser"]] structure
Optional<Label> query = new ComponentQuery.QueryBuilder()
        .fromLocation(headerLocation)
        .named("currentUser")
        .execute();
Label currentUser = query.get();
currentUser.setText("Welcome, " + name);
```
or
```java
Optional<Label> currentUser = new ComponentQuery.QueryBuilder()
        .fromLocation(headerLocation)
        .typed(Label.class)
        .execute();
```

### More on LocationContext
Location contexts can be decorated so the mental model of how to find an item reflects the application screen

Say we want to enable a MenuItem on our menu
```java
//Assume when constructing the menu
// Menu[name="Edit", children = [
//   Menu[name="Custom Options", children = [
//     MenuItem[name="Option 1"]
//     MenuItem[name="Option 2"]
//   ]]
//]]
LocationContext customMenu = new DefaultLocationContext("Edit")
        .decorateWith("Custom Options");
Optional<MenuItem> option1 = new ComponentQuery.QueryBuilder()
        .fromLocation(customMenu)
        .named("Option 1")
        .execute();
option1.get().setEnabled(true);
//performing different just for illustrative purposes
Optional<MenuItem> option2 = new ComponentQuery.QueryBuilder()
        .fromLocation(customMenu.decorateWith("Option 2"))
        .build()
        .execute();
option2.get().setEnabled(false);
```

### Custom Controls
Constructing forms individually can be painful if we have to specify every control individually.

JavaFX has several ways to build widgets for re-use, the recommended way is to extend Control and create a custom Skin for the control, specifying the skin in the createDefaultSkin() method
```java
public class MyControl extends Control {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    
    @Override
    protected Skin<?> createDefaultSkin() {
        return new MyControlSkin(this);
    }
    //generated getters/setters
}

public class MyControlSkin extends SkinBase<MyControl> {
    
    public MyControlSkin(MyControl control) {
        super(control);
        GridPane form = ControlsFactory.make(GridPane.class);
        
        Label nameLabel = ControlsFactory.make(Label.class, new LabelOptions()
                .text("Name"));
        TextField nameField = ControlsFactory.make(TextField.class, new TextOptions()
                .bindToTextProperty(control.nameProperty()));
        form.addRow(nameLabel, nameField);
        
        //slightly more work for Integer-String marshalling
        Label ageLabel = ControlsFactory.make(Label.class, new LabelOptions()
                .text("Age"));
        TextField ageField = ControlsFactory.make(TextField.class);
        //set up a one-way marshall since this is a form
        ageField.textProperty().addListener((prop, oldValue, newValue) -> {
           control.setAge(Integer.valueOf(newValue)); 
        });
        //if the value has already bean set, make sure the form has this value
        Integer currentValue = control.getAge();
        if (currentValue != null) {
            ageField.setText(currentValue.toString());
        }
                
        form.addRow(ageLabel, ageField);
        
        getChildren().add(form);
    }
}
```
If you want to register a factory to produce these widgets via ControlsFactory.make(MyControl.class) you can do so by adding your own NodeInstantiationService.
This is slightly more complicated and involves providing a NodeInstantiationService to instantiate your custom control. See the MaterialFX extension for how this can be done.
As such, it may be easier just to instantiate your custom controls manually.

```java
MyControl myControl = new MyControl();
//then add to you parent control/scene however you normally would
```

### i18n
When using the factories to construct components you can specify text or a resource key.

In the case of a resource key, if there is a resource bundle registered and the appropriate property files supplied, the resource key will be resolved for the locale.

```java
public class MyApplication extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    ApplicationOptions applicationOptions = new ApplicationOptions.Builder<>()
            //...
            //Step 1. declare your resource bundle as below
            .resourceBundle("com.legyver.fenxlib.samples.icon.demo")
            .build();

    //Step 2.  Use property keys for text
    BorderPaneApplicationLayout borderPaneApplicationLayout = new BorderPaneApplicationLayout.BorderPaneBuilder()
            .title("fenxlib.demo.title")//this will be internationalized
            .build();

    Menu fileMenu = new MenuBuilder()
            .name("fenxlib.demo.menu.label.file")//this will be internationalized
            .menuSection(new FileExitMenuSection())//the exit menu section is internationalized by default
            .build();

    Text text = ControlsFactory.make(Text.class, new TextOptions()
            .text("my.i18n.text"));//this will be internationalized
  }
}
```

### Notifications
Alerts are fired by calling various level methods on ApplicationContext
- infoAlert
- warningAlert
- errorAlert

These then hand them off to the AlertService implementation

```java
public class MyApplication extends Application {
  //..
  private AlertGeneratingForm alertGeneratingForm() {
    AlertGeneratingForm alertGeneratingForm = new AlertGeneratingForm();
    alertGeneratingForm.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (NumberUtils.isDigits(newValue)) {
        ApplicationContext.infoAlert(new AlertTextContent("fenxlib.demo.ok.message", newValue), 1000L);//timeout of 1 second
      } else if (NumberUtils.isParsable(newValue)) {
        ApplicationContext.warningAlert(new AlertTextContent("fenxlib.demo.warning.message", newValue), 2000L);//timeout of two seconds
      } else {
        ApplicationContext.errorAlert(new AlertTextContent("fenxlib.demo.error.message", newValue));//no timeout.  User must manually dismiss error
      }
    });
    return alertGeneratingForm;
  }
}
```

The [fenxlib.widgets.snackbar](fenxlib.widgets.snackbar) extension provides an AlertService implementation, displaying alerts over the specified region of the screen.

You can also provide your own AlertService to handle this.

### Configuration persistence
Config files are built into the framework to automatically remember things like last-opened files and recent files.

To leverage this, you must:
* Have a configuration module on the classpath like [fenxlib.config.json](fenxlib.config.json/README.md).
* Extend a ApplicationConfig bean like
  * CoreApplicationConfig which
    * Backs up recent file browsing
  * FileTreeConfig which
    * extends CoreApplicationConfig
    * Backs up the files/directories displayed in the file tree

Additionally, you can provide your own ConfigSection
```java
import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.section.ConfigPersisted;

public class MyConfigSection implements ApplicationVersionedConfigSection {
  private String myValue;
  /**
   * Below required for the application config.
   * Version must be <a href="https://semver.org/spec/v1.0.0.html">SemVer 1.0</a> compatible
   */
  private String version;
  private String sectionName = "MyApplication";

  //generated getters/setters
  // Note: Don't override getVersion() if you want to use the Application version for your version.
  // The default implementation of getVersion() in ApplicationVersionedConfigSection gets it from the application version.
  // It is set there by the ApplicationOptions-specified version or property file.
  // Note 2:  You can similarly not override getSectionName() if you're okay with using the application name
  // as set in ApplicationOptions as the name of your configuration.  This does mean that if you change
  // the name of your application, the configuration of previous versions will not be migrated
}
```
and inject it into the ApplicationConfig like

```java
import com.legyver.fenxlib.api.config.section.ConfigPersisted;

public class MyConfig extends ApplicationConfig {
  @ConfigPersisted
  private MyConfigSection myConfigSection;

  //generated getters/setters
}
```
The ConfigSection implementation can be versioned with  [JSONPath](https://github.com/json-path/JsonPath) migration

```java
import com.legyver.utils.jsonmigration.annotation.Migration;

public class Spec3 {
  private String version = "4.0.0";
  private Data3 data;
}

public class Data3 {
  @Migration(pre = "4.0.0", path = "$.data.named")//assume in v3, the 'renamed' field was moved to a POJO called 'data' and renamed 'named'
  @Migration(pre = "3.0.0", path = "$.renamed")//assume in v2 , the 'named' field was renamed to 'renamed'
  @Migration(pre = "2.0.0", path = "$.named")//assume v1 had a field called named on the root Spec
  private String name;//current version the field is called 'name' and is in a POJO called 'data'
}
```

### Extensions
- [fenxlib.extensions.materialfx](fenxlib.extensions.materialfx/README.MD)
    - an extension for MaterialFx support
```gradle
implementation group: 'com.legyver', name: 'fenxlib.extensions.materialfx', version: '3.0.2'
```
- [fenxlib.extensions.tuktukfx](fenxlib.extensions.tuktukfx/README.MD)
    - an extension for TukTukFx support
```gradle
implementation group: 'com.legyver', name: 'fenxlib.extensions.tuktukfx', version: '3.0.2'
```

### Widgets
All widgets are combinations of widgets created via the fenxlib.factories.api, so if you have fenxlib.factories.materialfx on your classpath it will use MaterialFX components, otherwise plain javafx widgets.

- [fenxlib.widgets.about](fenxlib.widgets.about/README.md)
  - an "About Page" widget that pre-populates license information upstream of any Legyver library
  - additional license information can also be added via a properties file
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.about', version: '3.0.2'
```
- [fenxlib.widgets.filetree](fenxlib.widgets.filetree/README.md)
  - a pre-made, customizable and extendable file explorer that monitors the filesystem for file operations on added files/folders.
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.filetree', version: '3.0.2'
```
- [fenxlib.widgets.snackbar](fenxlib.widgets.snackbar/README.md)
  - a notification widget that displays info/warning/error notifications in a snackbar
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.snackbar', version: '3.0.2'
```
### Skins
- [fenxlib.skins.number](fenxlib.skins.number/README.MD)
  - skins for showing numbers in text fields
    - currency
    - percentages
```gradle
implementation group: 'com.legyver', name: 'fenxlib.skins.number', version: '3.0.2'
```

### Controls
- [fenxlib.controls.icon](fenxlib.controls.icon/README.md)
  - Control that attaches an action to an icon
```gradle
implementation group: 'com.legyver', name: 'fenxlib.controls.icon', version: '3.0.2'
```

## Samples
Sample projects are available on the [fenxlib-samples](https://github.com/Legyver/fenxlib-samples) repository.
## Design
### Extensibility via META-INF/services
We use the [Java service mechanism](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html) to provide the correct implementation of various services.  The service lookup will always scan all services on the classpath, so there is nothing needed apart from adding a library to the classpath to have it work.

For example: our widget factories are in [fenxlib.core](fenxlib.core/README.MD)
This library also has default factories that returns a regular JavaFX widget.
However, if you add in the 'fenxlib.extensions.materialfx' library, you will get a MaterialFX widget.
You can further extend this by adding your own service to augment or usurp the existing widget factories.

To tie into this framework add an META-INF/services for file for the Service you are implementing.

### Services
| Service | Description                               | Defining module | Implementing Modules | Description |
| ------- |-------------------------------------------| --------------- |--------------| ----------- |
| IOService | Loads/saves files                         | fenxlib.api | fenxlib.core | Loads/saves files from disk |
| |                                           | | fenxlib.test | Loads test resources from classpath |
| FileMarshalService | Marshals Objects to/from appropriate format | fenxlib.api | fenxlib.core | Supports application/json|
| ConfigService | Provides the application configuration    | fenxlib.api | fenxlib.config.json | Saves in directory in appropriate location for filesystem
| ModuleVersionService | Provides the deployed version of a module | fenxlib.api | fenxlib.core | Sets the version of the fenxlib library from proprties at runtime
| LifecycleHookService | Initializes the application lifecycle hooks | fenxlib.api | fenxlib.core | Initializes the application lifecycle registry and hooks based on the configured application options
| AlertService | Handles application alerts                | fenxlib.api | fenxlib.core | Displays alerts in a popup over the defined location |
| IconLoaderService | Loads TTF Icons                           | fenxlib.core | fenxlib.icons.standard | Provides default IcoMoon-Free icons
| NodeInstantiationService | Instantiates controls for types of controls in javafx.controls module | fenxlib.core | fenxlib.core | Instantiates JavaFX controls 
| |                                           |  | fenxlib.extensions.materialfx | Instantiates MaterialFX controls 

#### examples
##### ControlsFactory

```java
public class Example {
  public TextField makeTextField() {
    return ControlsFactory.make(TextField.class);
  }
}
```
Will return a text field based on the preferred factory on the classpath.

The preference is numeric based on Integer.MIN_VALUE < pref < Integer.MAX_VALUE

| Module | Default value | Description |
| ------- | ------------- | ----------- |
| fenxlib.core | 0 | Produce plain JavaFX components |
| fenxlib.extensions.materialfx | -100 | Produce MaterialFX components |

### Services
#### ConfigService
Responsible for loading and saving config files.
The default ConfigService loads/saves files in JSON in the appropriate place for the OS
- Linux, MacOSX: The .{app.name} folder in $user.home
- Windows: %APPDATA%\Roaming\\{app.name}

#### AlertService

## Building this library
### Prerequisites
- This library is a Gradle project and uses a Gradle wrapper to manage the Gradle version.  Therefore, Gradle does not need to be installed.
- This library pulls in JavaFX as a maven dependency, as such does not require a JDK with JavaFX be installed.
- This library requires that the plain JDK 17+ be on the path to build.
- If you have a JDK installed that includes JavaFX, you may get warning messages in your IDE that a package is read from both package.a and package.b.  To resolve this, point it to a plain JDK that does not contain the JavaFX binaries.


### Building the library to your local repo
gradlew install

## Built With
* [Gradle](https://gradle.org/) - Dependency Management
* (1.X-2.X) [JFoenix](http://www.jfoenix.com/) - Material Design port library for JavaFX
* (3.X+) [MaterialFX](https://github.com/palexdev/MaterialFX) - Material Design port library for JavaFX

## Authors
* **Ben Arnold** - *Initial work* - [benfarnold](https://github.com/benfarnold)

## Licensing

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Legyver/fenxlib/blob/master/LICENSE)


## Versioning
Please follow [Semantic Versioning](https://semver.org/) conventions.

## Releases
* [Release Notes](https://github.com/Legyver/fenxlib/blob/master/RELEASE.MD)
