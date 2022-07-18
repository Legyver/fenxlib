package com.legyver.fenxlib.controls.icon;

import com.legyver.fenxlib.api.icons.options.IconOptions;
import com.legyver.fenxlib.core.icons.IconRegistry;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Skin for IconToggleControl
 */
public class IconToggleControlSkin extends SkinBase<IconToggleControl> {

    /**
     * Construct a skin for an icon toggle control
     * @param iconToggleControl the control to skin
     */
    public IconToggleControlSkin(IconToggleControl iconToggleControl) {
        super(iconToggleControl);
        IconOptions primaryIconOptions = new IconOptions.Builder<>()
                .family(iconToggleControl.getIconFontFamily())
                .icon(iconToggleControl.getIconName())
                .iconSize(iconToggleControl.getIconSize())
                .iconColor((Color) iconToggleControl.getIconPaint()).build();
        IconOptions secondaryIconOptions = new IconOptions.Builder<>()
                .family(iconToggleControl.getIconFontFamily())
                .icon(iconToggleControl.getAlternateIconName())
                .iconSize(iconToggleControl.getIconSize())
                .iconColor((Color) iconToggleControl.getIconPaint()).build();

        Text primaryIcon = IconRegistry.getInstance().getIcon(primaryIconOptions);
        Text secondaryIcon = IconRegistry.getInstance().getIcon(secondaryIconOptions);

        secondaryIcon.setVisible(false);
        iconToggleControl.showAlternateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                //show alternate
                primaryIcon.setVisible(false);
                secondaryIcon.setVisible(true);
            } else {
                primaryIcon.setVisible(true);
                secondaryIcon.setVisible(false);
            }
        });

        StackPane stackPane = new StackPane(primaryIcon, secondaryIcon);
        getChildren().add(stackPane);
    }
}
