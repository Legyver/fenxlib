package com.legyver.fenxlib.core.util;

import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.event.EventTarget;

import java.util.Map;

import static com.legyver.fenxlib.api.locator.IComponentRegistry.LOCATION_CONTEXT_PROPERTY;
import static com.legyver.fenxlib.api.locator.IComponentRegistry.LOCATION_CONTEXT_SIMPLE_NAME;

/**
 * Operator to handle re-registering a control
 */
public class LocationContextOperator {
    private final EventTarget eventTarget;
    private Map<Object, Object> propertyMap;

    /**
     * Contruct an operator for a control
     * @param eventTarget the control
     */
    public LocationContextOperator(EventTarget eventTarget) {
        this.eventTarget = eventTarget;
        this.propertyMap = new PropertyMapExtractor(eventTarget).get();
    }

    /**
     * Re-Register the control under new location context and de-register it under the old one.
     * This handles situations where a control is being moved from one location context to another.  An example of this
     * would be if a child-control was registered when it was constructed, and when added to a parent control, needs to
     * be re-registered under that parent.
     * @param parentContext the parent context
     * @param fallbackSimpleName the simple name to use if the control does not have a simple name already set on its property map.
     */
    public void reRegister(LocationContext parentContext, String fallbackSimpleName) {
        LocationContext previouslyRegisteredAs = getLocationContext();
        if (previouslyRegisteredAs != null) {
            Fenxlib.deregister(previouslyRegisteredAs);
        }
        String simpleName = (String) propertyMap.get(LOCATION_CONTEXT_SIMPLE_NAME);
        LocationContext regionLC;
        if (simpleName != null) {
            regionLC = parentContext.decorateWith(simpleName);
        } else {
            regionLC = parentContext.decorateWith(fallbackSimpleName);
        }
        Fenxlib.register(regionLC, eventTarget);
    }

    /**
     * Get the location context from the control
     * @return the location context as extracted from its property map
     */
    public LocationContext getLocationContext() {
        return (LocationContext) propertyMap.get(LOCATION_CONTEXT_PROPERTY);
    }
}
