package com.legyver.fenxlib.icons.standard;

import com.legyver.fenxlib.core.icons.service.FontMap;
import com.legyver.fenxlib.core.icons.service.UnknownIconDescriptionException;

/**
 * Font map to look up the unicode character for IcoMoon-Free font based on an icon name
 */
public class IcoMoonFontLookup implements FontMap {

    @Override
    public String getCode(String description) throws UnknownIconDescriptionException {
        String lookup = description.toUpperCase();

        IcoMoonFontEnum moonFontMap = null;
        try {
            moonFontMap = IcoMoonFontEnum.valueOf(lookup);
        } catch (Exception exception) {
            for (IcoMoonFontEnum font : IcoMoonFontEnum.values()) {
                if (font.getGlyphName().equalsIgnoreCase(description)) {
                    moonFontMap = font;
                    break;
                }
            }
        }
        if (moonFontMap == null) {
            throw new UnknownIconDescriptionException("No icon found matching description: " + description);
        }
        return moonFontMap.getUnicode();
    }
}
