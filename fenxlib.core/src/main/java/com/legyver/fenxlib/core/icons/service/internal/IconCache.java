package com.legyver.fenxlib.core.icons.service.internal;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.core.icons.fonts.FontMap;
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

    private final Map<String, FontMap> fontMapsByFamily = new HashMap<>();
    private final Map<String, FontContext> fontCtxByFamily = new HashMap<>();
    private final List<LazyFontInstaller> lazyFontInstallers = new ArrayList<>();

    /**
     * Eagerly install all fonts so they can be used by css
     */
    public void installAll() {
        if (fontMapsByFamily.isEmpty()) {
            synchronized (this) {
                if (fontMapsByFamily.isEmpty()) {
                    logger.debug("Installing all fonts");
                    installFonts(20.0);
                }
            }
        }
    }

    private synchronized void installFonts(Double size) {
        if (fontMapsByFamily.isEmpty()) {
            logger.debug("Installing fonts");
            for (LazyFontInstaller lazyFontInstaller : lazyFontInstallers) {
                String family = lazyFontInstaller.getFontFamily();

                if (!fontMapsByFamily.containsKey(family)) {
                    logger.debug("Installing font: {}", family);
                    Font font = lazyFontInstaller.install(size);
                    fontMapsByFamily.put(font.getFamily(), lazyFontInstaller.getFontMap());
                    fontCtxByFamily.put(font.getFamily(), new FontContext(size, font));
                    logger.debug("Font {} installed", font.getFamily());
                } else {
                    logger.debug("Skipping font {} as already installed", family);
                }
            }
        }
    }
    /**
     * Get the text icon.
     * @param iconOptions options to specify the text
     * @return the text
     */
    public Text get(IconOptions iconOptions) {
        Text text = null;
        if (fontMapsByFamily.isEmpty()) {
            Collections.sort(lazyFontInstallers);
            installFonts(iconOptions.getIconSize());
        }
        FontMap fontMap = fontMapsByFamily.get(iconOptions.getFamily());
        FontContext context = fontCtxByFamily.get(iconOptions.getFamily());
        if (fontMap != null && context != null) {
            logger.debug("FontMap found for family {}", iconOptions.getFamily());
            text = new Text();
            String icon = null;
            try {
                icon = fontMap.getCode(iconOptions);
            } catch (UnknownIconDescriptionException e) {
                logger.error("Unable to load {} from {}", iconOptions.getIcon(), iconOptions.getFamily());
            }
            text.setText(icon);
            text.setFont(context.get(iconOptions.getIconSize()));

//            text.setFont(Font.font(iconOptions.getFamily(), iconOptions.getIconSize()));
            logger.debug("font {} set", iconOptions.getIcon());
            Paint color = iconOptions.getColor();
            if (color == null) {
                color = Paint.valueOf(iconOptions.getIconColorString());
            }
            text.setFill(color);
            logger.trace("iconName: {}, iconCode: {}, iconSize: {}, iconColor: {}", iconOptions.getIcon(), icon, iconOptions.getIconSize(), iconOptions.getIconColorString());
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
