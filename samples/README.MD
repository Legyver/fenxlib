# Samples
Contains samples for any of the libraries and extensions

## Contributing
Please put your sample in a folder called the same as the main folder, ie one of:
- core
- extensions
- skins
- widgets

These samples are for reference and are not shipped, so the projects should not be added to the root settings.gradle

Use the base package of com.legyver.fenxlib.samples.\<name>
### Example
- widgets
    - widget-about
        - build.gradle
        - src
            - main
                - java
                    - com
                        - legyver
                            - fenxlib
                                - samples
                                    - about
                                        - MyDemo.java
