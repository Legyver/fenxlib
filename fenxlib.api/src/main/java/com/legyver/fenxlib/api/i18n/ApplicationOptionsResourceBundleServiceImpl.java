package com.legyver.fenxlib.api.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBundleServiceImpl for any resource bundles specified by ApplicationOptions
 * i18n properties loaded by this resource bundle service will supersede any of the core or other default values shipped.
 * This is done so that devs can override the out-of-the-box values supplied with the fenxlib library.
 * See the icon sample for a demonstration of this (overrides the File {@literal >} "Exit" text to read "Quit")
 */
public class ApplicationOptionsResourceBundleServiceImpl implements ResourceBundleService {
    private List<String> bundlesToScan;

    /**
     * Set the bundles to scan
     * @param bundlesToScan bundles to scan
     */
    public void setBundlesToScan(List<String> bundlesToScan) {
        this.bundlesToScan = bundlesToScan;
    }

    @Override
    public List<ResourceBundle> getResourceBundles(Locale locale) {
        List<ResourceBundle> resourceBundles = new ArrayList<>();
        for (String bundle : bundlesToScan) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle, locale);
            resourceBundles.add(resourceBundle);
        }
        return resourceBundles;
    }

    @Override
    public int order() {
        return -100;
    }
}
