package com.legyver.fenxlib.core.icons.fonts;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.core.icons.service.UnknownIconDescriptionException;

/**
 * Convert a description into the appropriate code
 */
public interface FontMap {
    /**
     * Get the code for the given description
     * @param iconOptions icon options containing the description to lookup
     * @return the code
     * @throws UnknownIconDescriptionException if the icon requested is unknown
     */
    String getCode(IconOptions iconOptions) throws UnknownIconDescriptionException;
}