# fenxlib.config.json
Support for JSON config files for fenxlib applications
```gradle
implementation group: 'com.legyver', name: 'fenxlib.config.json', version: '3.0.0-beta.8'
```

**Note:** If your consuming application is modular, you will need to provide automatic modules for several legacy dependencies.
You can use the legacy-java-module-info plugin to do so.

**build.gradle**
```groovy
plugins {
  id 'com.legyver.legacy-java-module-info' version '1.0.0'
}

legacyJavaModuleInfo {
    automaticModule('json-path-2.7.0.jar', 'com.jayway.jsonpath')
    automaticModule('json-smart-2.4.7.jar', 'net.minidev.json')
    automaticModule('accessors-smart-2.4.7.jar', 'net.minidev.accessors')
}
```
