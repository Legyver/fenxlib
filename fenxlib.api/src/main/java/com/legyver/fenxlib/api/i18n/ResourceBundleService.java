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
     * Get the resource bundles for the specified locale
     *
     * @param locale the locale
     * @return the resource bundle
     */
    List<ResourceBundle> getResourceBundles(Locale locale);

}
