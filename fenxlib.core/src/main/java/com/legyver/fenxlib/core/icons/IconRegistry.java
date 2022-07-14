package com.legyver.fenxlib.core.icons;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.core.icons.fonts.FontMap;
import com.legyver.fenxlib.core.icons.service.internal.IconCache;
import com.legyver.fenxlib.core.icons.service.internal.IconServiceRegistry;
import com.legyver.fenxlib.core.icons.service.internal.LazyFontInstaller;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

/**
 * Registry of icons
 */
public class IconRegistry {
    private static final Logger logger = LogManager.getLogger(IconRegistry.class);

    private final IconCache cache;
    private static IconRegistry instance;

    /**
     * Construct a registry for Icon Store Services
     */
    private IconRegistry() {
        //singleton
        cache = new IconCache();
    }

    /**
     * Get the singleton instance of the service registry for IconServices
     * @return the singleton instance
     */
    public static IconRegistry getInstance() {
        if (instance == null) {
            synchronized (IconRegistry.class) {
                if (instance == null) {
                    instance = new IconRegistry();
                }
                try {
                    IconServiceRegistry.getInstance().loadIcons();
                    instance.cache.installAll();
                } catch (CoreException e) {
                    logger.error("Error loading icons", e);
                }
            }
        }
        return instance;
    }

    /**
     * Load all icons from all IconStoreServices that are on the classpath
     * @param iconOptions options describing the icon
     * @return an IconPane wrapping the icon
     */
    public Text getIcon(IconOptions iconOptions) {
        Text text = cache.get(iconOptions);
        return text;
    }

    /**
     * Load the icons from the appropriate input stream
     * @param inputStream the source of icons
     * @param fontFamily the family of the font
     * @param fontMap the fontMap to convert icon descriptions to the character
     * @param preference the preference for this font implementation
     */
    public void load(InputStream inputStream, String fontFamily, FontMap fontMap, int preference) {
        cache.register(new LazyFontInstaller(inputStream, fontFamily, fontMap, preference));
    }
}