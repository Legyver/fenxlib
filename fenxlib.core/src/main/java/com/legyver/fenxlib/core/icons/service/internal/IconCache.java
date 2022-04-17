package com.legyver.fenxlib.core.icons.service.internal;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.core.icons.service.FontMap;
import com.legyver.fenxlib.core.icons.service.UnknownIconDescriptionException;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.List;

/**
 * Icon cache.
 * Lazily installs fonts on first read to allow for overriding
 */
public class IconCache {
    private static final Logger logger = LogManager.getLogger(IconCache.class);

    private final Map<String, FontMap> fontsByFamily = new HashMap<>();
    private final List<LazyFontInstaller> lazyFontInstallers = new ArrayList<>();

    /**
     * Get the text icon.
     * @param iconOptions options to specify the text
     * @return the text
     */
    public Text get(IconOptions iconOptions) {
        Text text = null;
        if (fontsByFamily.isEmpty()) {
            Collections.sort(lazyFontInstallers);
            synchronized (this) {
                if (fontsByFamily.isEmpty()) {
                    logger.debug("Installing fonts");
                    for (LazyFontInstaller lazyFontInstaller : lazyFontInstallers) {
                        String family = lazyFontInstaller.getFontFamily();

                        if (!fontsByFamily.containsKey(family)) {
                            logger.debug("Installing font: {}", family);
                            Font font = lazyFontInstaller.install(iconOptions.getIconSize());
                            fontsByFamily.put(font.getFamily(), lazyFontInstaller.getFontMap());
                            logger.debug("Font {} installed", font.getFamily());
                        } else {
                            logger.debug("Skipping font {} as already installed", family);
                        }
                    }
                }
            }
        }
        FontMap fontMap = fontsByFamily.get(iconOptions.getFamily());
        if (fontMap != null) {
            logger.debug("FontMap found for family {}", iconOptions.getFamily());
            text = new Text();
            String icon = null;
            try {
                icon = fontMap.getCode(iconOptions.getIcon());
            } catch (UnknownIconDescriptionException e) {
                logger.error("Unable to load {} from {}", iconOptions.getIcon(), iconOptions.getFamily());
            }
            text.setText(icon);
            text.setFont(Font.font(iconOptions.getFamily(), iconOptions.getIconSize()));
            logger.debug("font {} set", iconOptions.getIcon());
            Paint color = Paint.valueOf(iconOptions.getIconColor());
            text.setFill(color);
            logger.trace("iconName: {}, iconCode: {}, iconSize: {}, iconColor: {}", iconOptions.getIcon(), icon, iconOptions.getIconSize(), iconOptions.getIconColor());
        } else {
            logger.debug("FontMap found for family {}", iconOptions.getFamily());
        }
        return text;
    }

    /**
     * Register a font installer for lazy installation
     * @param fontInstaller the font installer
     */
    public void register(LazyFontInstaller fontInstaller) {
        lazyFontInstallers.add(fontInstaller);
        logger.debug("Staged lazy installer for font: {}", fontInstaller.getFontFamily());
    }

}
