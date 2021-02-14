# fenxlib
A UI framework for JavaFX.

Note: The distribution of this library is compiled on Java 15.

## Usage
Since version 2.0.0.0-alpha-3, this library has been made module-friendly, and hence the artifacts use dots as separators instead of traditional dashes.

The main functionality of this library is in the fenxlib.core.impl module.

```gradle
implementation group: 'com.legyver', name: 'fenxlib.core.impl', version: '2.0.0.0-alpha-5'
```

There are several extensions, widgets and skins available as well as independent dependencies

### Extensions
- [fenxlib.extensions.tuktukfx](fenxlib.extensions.tuktukfx/README.MD)
    - an extension for TukTukFx support
```gradle
implementation group: 'com.legyver', name: 'fenxlib.extensions.tuktukfx', version: '2.0.0.0-alpha-5'
```

### Widgets
- [fenxlib.widgets.about](fenxlib.widgets.about/README.md)
  - an "About Page" widget that pre-populates license information downstream of any Legyver library
  - additional license information can also be added via a properties file
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.about', version: '2.0.0.0-alpha-5'
```
- [fenxlib.widgets.blade](fenxlib.widgets.blade/README.md)
  - a pre-made form that lays out form-fields on a grid
```gradle
implementation group: 'com.legyver', name: 'fenxlib.widgets.blade', version: '2.0.0.0-alpha-5'
```

### Skins
- [fenxlib.skins.number](fenxlib.skins.number/README.MD)
  - skins for showing numbers in text fields
    - currency
    - percentages
```gradle
implementation group: 'com.legyver', name: 'fenxlib.skins.number', version: '2.0.0.0-alpha-5'
```

## Samples
Sample projects are available on the [fenxlib-samples](https://github.com/Legyver/fenxlib-samples) repository.

## Getting Started
### Prerequisites
This is a gradle project.  You will either need an IDE that supports gradle, or have gradle installed in order to build.  Since this uses modules, it is recommended to use gradle 6.7 or newer.

### Building the library to your local repo
gradlew install

## Built With
* [Gradle](https://gradle.org/) - Dependency Management
* [JFoenix](http://www.jfoenix.com/) - Material Design port library for JavaFX

## Authors
* **Ben Arnold** - *Initial work* - [benfarnold](https://github.com/benfarnold)

## Licensing

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Legyver/fenxlib/blob/master/LICENSE)


## Versioning
Release.Breaking.Feature.Fix
- Release: Used for major milestone releases.
- Breaking: Used when the change breaks backward compatibility.
- Feature: Used when introducing features that do not break backward compatability.
- Fix: Used for small bug fixes

## Releases
* [Release Notes](https://github.com/Legyver/fenxlib/blob/master/RELEASE.MD)
