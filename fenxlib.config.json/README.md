# fenxlib.config.json
Support for JSON config files for fenxlib applications
```gradle
implementation group: 'com.legyver', name: 'fenxlib.config.json', version: '3.0.0-beta.9'
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

**Note:**
You will need to additionally either expose your config class to com.legyver.utils.ruffles, or org.apache.commons.lang3
```java
module example {
    requires com.legyver.fenxlib.config.json;

    exports com.legyver.selexml.app.config to com.fasterxml.jackson.databind, com.legyver.utils.ruffles;
    opens com.legyver.selexml.app.config to com.legyver.fenxlib.config.json;
}
```