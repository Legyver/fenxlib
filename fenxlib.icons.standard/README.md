# fenxlib standard icons
Standard icon font for the fenxlib library.  This is based on IcoMoon
## Usage
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.icons.standard', version: '3.0.0'
```

## Example
```java
public class MyControlSkin extends SkinBase<MyControl> {
    public MyControlSkin(MyControl control) {
        super(control);
        IconOptions iconOptions = new IcoMoonIconOptions.Builder()
                .icoMoonIcon(IcoMoonFontEnum.ICON_PLAY)
                .iconColor(Color.GREEN)
                .iconSize(20)
                .build();
        Text icon = IconRegistry.getInstance().getIcon(iconOptions);
        getChildren().add(icon);
    }
}
```