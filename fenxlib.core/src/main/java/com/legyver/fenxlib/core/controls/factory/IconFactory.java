package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.icons.IconRegistry;
import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.text.Text;

/**
 * Factory to create icons based on whatever IconLoaderService(s) are on the classpath
 */
public class IconFactory implements StyleableFactory<Text> {
    private final IconOptions iconOptions;

    /**
     * Construct an IconFactory to create an icon based on the provided options and the libraries on the classpath.
     * @param iconOptions the options to use to create an Icon
     */
    public IconFactory(IconOptions iconOptions) {
        this.iconOptions = iconOptions;
    }

    @Override
    public Text makeNode(LocationContext locationContext) throws CoreException {
        return IconRegistry.getInstance().getIcon(iconOptions);
    }
}
