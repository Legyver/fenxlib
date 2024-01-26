# fenxlib.config.json
Support for JSON config files for fenxlib applications
```gradle
implementation group: 'com.legyver', name: 'fenxlib.config.json', version: '3.0.2'
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