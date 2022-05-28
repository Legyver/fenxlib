# Integration testing your fenxlib applications
This project provides a base class and configs to get started with integration testing your fenxlib applications

# Provided functionality
## @BeforeEach
- Everything supported by TestFx ApplicationTest @BeforeEach 
- Startup the Fenxlib application framework using TestFX ApplicationAdapter.  This
  - Scans the extending test for [@FenxlibConfiguration](src/main/java/com/legyver/fenxlib/tests/base/config/annotation/FenxlibConfiguration.java)
    - If supplied, loads the configuration from the specified file.
  - Registers the primary stage with Fenxlib
  - Executes the remaining application lifecycle hooks up to and including POST_INIT

## @AfterEach
- Everything supported by the TestFx ApplicationTest @AfterEach
- Reset the application state

# Usage
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.tests.base', version: '3.0.0-alpha.3'
```

```java
public class MyTest extends FenxlibTest {

    @Test
    public void testMyApplication() throws Exception {
        //test code
    }
}
```