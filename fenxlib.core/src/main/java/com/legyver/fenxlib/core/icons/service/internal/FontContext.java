package com.legyver.fenxlib.core.icons.service.internal;

import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

/**
 * Context for managing fonts
 */
public class FontContext {
    private final Map<Double, Font> fontsBySize = new HashMap<>();

    /**
     * Construct a context with a given font and size
     * @param size the installed size
     * @param font the installed font of that size
     */
    public FontContext(Double size, Font font) {
        fontsBySize.put(size, font);
    }

    /**
     * Get the font of the specified size
     * @param size the size
     * @return the installed font
     */
    public Font get(Double size) {
        return fontsBySize.get(size);
    }

}
