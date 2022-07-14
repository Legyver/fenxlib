package com.legyver.fenxlib.icons.standard;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.core.icons.fonts.FontMap;
import com.legyver.fenxlib.core.icons.service.UnknownIconDescriptionException;

/**
 * Font map to look up the unicode character for IcoMoon-Free font based on an icon name
 */
public class IcoMoonFontLookup implements FontMap {

    @Override
    public String getCode(IconOptions iconOptions) throws UnknownIconDescriptionException {
        if (iconOptions instanceof IcoMoonIconOptions && ((IcoMoonIconOptions) iconOptions).getIcoMoonFont() != null) {
            return ((IcoMoonIconOptions) iconOptions).getIcoMoonFont().getUnicode();
        }
        String lookup = iconOptions.getIcon().toUpperCase();

        IcoMoonFontEnum moonFontMap = null;
        try {
            moonFontMap = IcoMoonFontEnum.valueOf(lookup);
        } catch (Exception exception) {
            for (IcoMoonFontEnum font : IcoMoonFontEnum.values()) {
                if (font.getGlyphName().equalsIgnoreCase(iconOptions.getIcon())) {
                    moonFontMap = font;
                    break;
                }
            }
        }
        if (moonFontMap == null) {
            throw new UnknownIconDescriptionException("No icon found matching description: " + iconOptions.getIcon());
        }
        return moonFontMap.getUnicode();
    }
}
