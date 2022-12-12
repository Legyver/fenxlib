package com.legyver.fenxlib.api.i18n;

import com.legyver.fenxlib.api.service.OrderedServiceDelegator;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.*;

/**
 * Registry for Resource Bundles to scan for property keys
 */
public class ResourceBundleServiceRegistry {

    private final OrderedServiceDelegator<ResourceBundleService> orderedServiceDelegator;
    private static ResourceBundleServiceRegistry instance;
    private List<String> customBundleBaseNames;
    private boolean firstPass = true;

    private ResourceBundleServiceRegistry() {
        ServiceLoader<ResourceBundleService> alertServiceServiceLoader = ServiceLoader.load(ResourceBundleService.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(alertServiceServiceLoader);
    }

    /**
     * Retrieve a singleton instance of the registry
     * @return the instance
     */
    public static ResourceBundleServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (ResourceBundleServiceRegistry.class) {
                if (instance == null) {
                    instance = new ResourceBundleServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Get the message.  Delegates to {@link #getMessage(Locale, String, Object...)} with default locale
     * Check the comments there.
     * @param propertyKey the property key to lookup
     * @param args optional args to apply
     * @return the message or the original property key if unresolved
     */
    public String getMessage(String propertyKey, Object...args) {
        return getMessage(Locale.getDefault(), propertyKey, args);
    }

    /**
     * Get the i18n message for a propertyKey and locale.
     * Returns the first not empty message resolved in the following order
     *  1. Any service that is higher precedence (lower order number) and contains the value
     *  2. Resource Bundles are scanned in the order they are specified in the {@link com.legyver.fenxlib.api.config.options.ApplicationOptions.Builder#resourceBundle(String)}
     *     with the default "com.legyver.fenxlib.core.i18n" being evaluated last.
     *
     * @param locale the locale
     * @param propertyKey the property key
     * @param args any arguments that need to be resolved
     * @return the message or the unresolved property key
     */
    public String getMessage(Locale locale, String propertyKey, Object...args) {
        String result = null;
        if (propertyKey != null) {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            boolean setFirstPass = false;
            for (Iterator<ResourceBundleService> it = orderedServiceDelegator.iterator(); result == null && it.hasNext(); ) {
                ResourceBundleService resourceBundleService = it.next();
                if (firstPass && resourceBundleService instanceof ApplicationOptionsResourceBundleServiceImpl) {
                    ((ApplicationOptionsResourceBundleServiceImpl) resourceBundleService).setBundlesToScan(customBundleBaseNames);
                    setFirstPass = true;
                }
                result = evaluateBundle(locale, propertyKey, result, resourceBundleService, args);
            }
            if (setFirstPass) {
                //we do this so if there are more than one ApplicationOptionsResourceBundleServiceImpl (as in, the dev supplied their own),
                //they are all processed
                firstPass = false;
            }
        }
        if (result == null) {
            result = propertyKey;
        }
        return result;
    }

    private String evaluateBundle(Locale locale, String propertyKey, String result, ResourceBundleService resourceBundleService, Object[] args) {
        List<ResourceBundle> resourceBundles = resourceBundleService.getResourceBundles(locale);
        if (resourceBundles != null) {
            for (ResourceBundle resourceBundle : resourceBundles) {
                if (resourceBundle.containsKey(propertyKey)) {
                    result = resourceBundle.getString(propertyKey);
                    if (StringUtils.isNotEmpty(result)) {
                        if (args != null && args.length > 0) {
                            result = MessageFormat.format(result, args);
                        }
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Set the bundle names passed in via {@link com.legyver.fenxlib.api.config.options.ApplicationOptions.Builder#resourceBundle(String)}
     * @param bundleBaseNames any custom names to scan
     */
    public void setBundleBaseNames(List<String> bundleBaseNames) {
        this.customBundleBaseNames = bundleBaseNames;
    }
}
