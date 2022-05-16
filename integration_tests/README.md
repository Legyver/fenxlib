# Blackbox Integration Tests
## Purpose
Allow for integration tests for the Fenxlib framework

## Conventions
Please keep your tests aligned with the module you're testing and use the it (integration test) package as a substitute for com.legyver.fenxlib package for brevity and to avoid split packages.


Examples:

| Project module | Test project | Package tested | Test package |
| -------------- | ------------ | -------------- | ------------ |
| fenxlib.core   | it.core      | com.legyver.fenxlib.core.config | it.core.config |
| fenxlib.core   | it.core      | com.legyver.fenxlib.core.locator.query | it.core.locator.query |
| fenxlib.widgets.about   | it.widgets.about      | com.legyver.fenxlib.widgets.license | it.widgets.license |



