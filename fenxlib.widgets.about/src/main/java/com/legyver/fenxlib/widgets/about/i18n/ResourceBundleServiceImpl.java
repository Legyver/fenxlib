package com.legyver.fenxlib.widgets.about.i18n;

import com.legyver.fenxlib.api.i18n.ResourceBundleService;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Resource bundle service for loading resources for the about page
 */
public class ResourceBundleServiceImpl implements ResourceBundleService {
    @Override
    public ResourceBundle getResourceBundle(String bundleBaseName, Locale locale) {
        return ResourceBundle.getBundle(bundleBaseName, locale);
    }

    @Override
    public List<String> getAdditionalBundleNames() {
        return Arrays.asList("com.legyver.fenxlib.widgets.about.i18n.legyver-defaults");
    }

    @Override
    public int order() {
        return 0;
    }
}
