package com.legyver.fenxlib.widgets.filetree.i18n;

import com.legyver.fenxlib.api.i18n.ResourceBundleService;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Resource bundle service for loading
 */
public class ResourceBundleServiceImpl implements ResourceBundleService {
    @Override
    public List<ResourceBundle> getResourceBundles(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.legyver.fenxlib.widgets.filetree.i18n.legyver-defaults", locale);
        return Arrays.asList(resourceBundle);
    }

    @Override
    public int order() {
        return 0;
    }
}
