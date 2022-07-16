package com.legyver.fenxlib.api.icons.fonts;

import com.legyver.fenxlib.api.icons.options.IconOptions;
import com.legyver.fenxlib.api.icons.exception.UnknownIconDescriptionException;

/**
 * Convert a description into the appropriate code
 */
public interface FontMap {
    /**
     * Get the code for the given description
     * @param iconOptions icon options containing the description to lookup
     * @return the unicode for the icon
     * @throws UnknownIconDescriptionException if the icon requested is unknown
     */
    String getUnicode(IconOptions iconOptions) throws UnknownIconDescriptionException;
}
