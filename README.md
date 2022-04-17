# fenxlib
A UI framework for JavaFX.

Note: The distribution of this library is compiled on Java 17.

## Design
### Extensibility via META-INF/services
We use the [Java service mechanism](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html) to provide the correct implementation of various services.  The service lookup will always scan all services on the classpath, so there is nothing needed apart from adding a library to the classpath to have it work.

For example: our widget factories are in [fenxlib.factories.api](fenxlib.factories.api/README.MD)
This same library has default factories that returns a JavaFX widget.
However, if you add in the 'fenxlib.factories.materialfx' library, you will get a MaterialFX widget.
You can further extend this by adding your own service to augment or usurp the existing widget factories.

To tie into this framework add an META-INF/services for file for the Service you are implementing.

### Notifications


### Services
| Service | Description | Defining module | Implementing Modules | Description |
| ------- | ----------- | --------------- | -------------------- | ----------- |
| ConfigService | Loads/saves the application configuration | fenxlib.core | fenxlib.core | Saves in directory in appropriate location for filesystem
| AlertService | Handles application alerts | fenxlib.core | fenxlib.core | Displays alerts in a popup over the defined location |
| IconLoaderService | Loads TTF Icons | fenxlib.core | fenxlib.icons.standard | Provides default IcoMoon-Free icons
| NodeInstantiationService | Instantiates controls for types of controls in javafx.controls module | fenxlib.core | fenxlib.core | Instantiates JavaFX controls 
| | |  | fenxlib.controls.materialfx | Instantiates MaterialFX controls 

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
| fenxlib.controls.materialfx | -100 | Produce MaterialFX components |

### Services
#### ConfigService
Responsible for loading and saving config files.
The default ConfigService loads/saves files in JSON in the appropriate place for the OS
- Linux, MacOSX: The .{app.name} folder in $user.home
- Windows: %APPDATA%\Roaming\{app.name}

#### AlertService

## Usage
Since version 2.0.0.0, this library has been made module-friendly, and hence the artifacts use dots as separators instead of traditional dashes.

The main functionality of this library is in the fenxlib.core.impl module.

```gradle
implementation group: 'com.legyver', name: 'fenxlib.core.impl', version: '2.1.2.6'
```

There are several extensions, widgets and skins available as well as independent dependencies

### Extensions
- [fenxlib.extensions.tuktukfx](fenxlib.extensions.tuktukfx/README.MD)
    - an extension for TukTukFx support
```gradle
implementation group: 'com.legyver', name: 'fenxlib.extensions.tuktukfx', version: '2.1.2.6'
```

### Widgets
All widgets are combinations of widgets created via the fenxlib.factories.api, so if you have fenxlib.factories.materialfx on your classpath it will use MaterialFX components, otherwise plain javafx widgets.

- [fenxlib.widgets.about](fenxlib.widgets.about/README.md)
  - an "About Page" widget that pre-populates license information upstream of any Legyver library
  - additional license information can also be added via a properties file
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.about', version: '2.1.2.6'
```
- [fenxlib.widgets.blade](fenxlib.widgets.blade/README.md)
  - a pre-made form that lays out form-fields on a grid
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.blade', version: '2.1.2.6'
```
- [fenxlib.widgets.filetree](fenxlib.widgets.filetree/README.md)
  - a pre-made, customizable and extendable file explorer that monitors the filesystem for file operations on added files/folders.
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.filetree', version: '2.1.2.6'
```
- [fenxlib.widgets.snackbar](fenxlib.widgets.snackbar/README.md)
  - a notification widget that displays info/warning/error notifications in a snackbar
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.snackbar', version: '2.1.2.6'
```
### Skins
- [fenxlib.skins.number](fenxlib.skins.number/README.MD)
  - skins for showing numbers in text fields
    - currency
    - percentages
```gradle
implementation group: 'com.legyver', name: 'fenxlib.skins.number', version: '2.1.2.6'
```

### Controls
- [fenxlib.controls.svg](fenxlib.controls.svg/README.md)
  - SVG control that attaches an action to an icon
```gradle
implementation group: 'com.legyver', name: 'fenxlib.controls.svg', version: '2.1.2.6'
```

## Samples
Sample projects are available on the [fenxlib-samples](https://github.com/Legyver/fenxlib-samples) repository.

## Getting Started
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
Release.Breaking.Feature.Fix
- Release: Used for major milestone releases.
- Breaking: Used when the change breaks backward compatibility.
- Feature: Used when introducing features that do not break backward compatibility.
- Fix: Used for small bug fixes

## Releases
* [Release Notes](https://github.com/Legyver/fenxlib/blob/master/RELEASE.MD)
