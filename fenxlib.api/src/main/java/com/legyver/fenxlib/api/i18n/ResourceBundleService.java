package com.legyver.fenxlib.api.i18n;

import com.legyver.fenxlib.api.service.OrderedService;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Service to load resource messages for i18n
 */
public interface ResourceBundleService extends OrderedService<ResourceBundleService> {
    /**
     * Get the resource bundle for the specified locale
     *
     * @param bundleBaseName the base name
     * @param locale the locale
     * @return the resource bundle
     */
    ResourceBundle getResourceBundle(String bundleBaseName, Locale locale);

    List<String> getAdditionalBundleNames();

}
