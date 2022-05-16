## Blackbox testing core
### Reason
Since fenxlib.core is modular, and relies on services (technologies that don't play particularly nice with testing), we move the test to a blackbox module.

Unit tests that to not rely on services can of course remain in the src/test folders of the relevant modules.

### Directory structure
- src\test\java
  - where your tests go
- src\test\resources
  - any resources required for your tests
