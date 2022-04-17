package com.legyver.fenxlib.icons.standard.service;

import com.legyver.fenxlib.core.icons.service.IconLoaderService;
import com.legyver.fenxlib.core.icons.service.FontAccessData;
import com.legyver.fenxlib.icons.standard.IcoMoonFontLookup;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Load the standard font for icons
 * The prefix for these fonts will be {@link IconLoaderServiceImpl#STANDARD_ICON_FONT}
 */
public class IconLoaderServiceImpl implements IconLoaderService {

    /**
     * family for standard icon fonts
     */
    public static final String STANDARD_ICON_FONT = "icomoon";

    @Override
    public List<FontAccessData> iconsToLoad() {
        return Arrays.asList(getAccessData("icomoon.ttf", STANDARD_ICON_FONT));
    }

    private FontAccessData getAccessData(String name, String fontFamily) {
        InputStream inputStream = getClass().getResourceAsStream(name);
        return new FontAccessData(inputStream, new IcoMoonFontLookup(), fontFamily);
    }
}
