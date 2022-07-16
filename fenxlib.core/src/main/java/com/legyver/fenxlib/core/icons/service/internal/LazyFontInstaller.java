package com.legyver.fenxlib.core.icons.service.internal;

import com.legyver.fenxlib.api.icons.fonts.FontMap;
import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Install fonts on first access.
 */
public class LazyFontInstaller implements Comparable<LazyFontInstaller> {
    private final InputStream inputStream;
    private final String fontFamily;
    private final FontMap fontMap;
    private final Integer preference;

    /**
     * Construct a font installer to install the font on-demand
     * @param inputStream the input stream containing the font data
     * @param fontFamily the font family
     * @param fontMap the description to code mapping
     * @param preference the preference for this font map (higher number is lower preference)
     */
    public LazyFontInstaller(InputStream inputStream, String fontFamily, FontMap fontMap, int preference) {
        this.inputStream = inputStream;
        this.fontFamily = fontFamily;
        this.fontMap = fontMap;
        this.preference = preference;
    }

    /**
     * Install the font
     * @param size the default size of the font
     * @return the installed font
     */
    public Font install(double size) {
        Font font = Font.loadFont(inputStream, size);
        return font;
    }

    /**
     * Get the font family
     * @return the font family
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * Get the font map
     * @return the font map
     */
    public FontMap getFontMap() {
        return fontMap;
    }

    @Override
    public int compareTo(LazyFontInstaller o) {
        //sort from lowest to highest
        return preference.compareTo(o.preference);
    }
}
