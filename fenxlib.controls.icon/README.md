# fenxlib Icon Control skins
Standalone control for turning an icon into a control

- IconControl
    - IconControlSkin

## Usage
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.controls.icon', version: '3.0.2'
```

You will also need to provide an Icon font library.

Fenxlib contains fenxlib.icons.standard for this purpose which provides an IcoMoon icon font

## Example
Note: In the below example, the free IcoMoon IconFont provided by adding the fenxlib.icons.standard library to the classpath.

**build.gradle**
```groovy
dependencies {
  implementation group: 'com.legyver', name: 'fenxlib.controls.icon', version: '3.0.2'
  implementation group: 'com.legyver', name: 'fenxlib.icons.standard', version: '3.0.2'
}
```

**MySkin.java**
```java
public class MySkin extends SkinBase<MyControl> {
    public MySkin(MyControl control) {
        super(control);
        IconControl iconControl = new IconControl();
        IconOptions iconOptions = new IcoMoonIconOptions.Builder()
              .icoMoonIcon(IcoMoonFontEnum.ICON_PLAY)
              .iconColor(Color.GREEN)
              .iconSize(20)
              .build();
        iconControl.setIconOptions(iconOptions);
        getChildren().add(iconControl);
    }
}
```