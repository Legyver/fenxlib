package com.legyver.fenxlib.core.util;

import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.event.EventTarget;

import java.util.Map;

import static com.legyver.fenxlib.api.locator.IComponentRegistry.LOCATION_CONTEXT_PROPERTY;
import static com.legyver.fenxlib.api.locator.IComponentRegistry.LOCATION_CONTEXT_SIMPLE_NAME;

public class LocationContextOperator {
    private final EventTarget eventTarget;
    private Map<Object, Object> propertyMap;

    public LocationContextOperator(EventTarget eventTarget) {
        this.eventTarget = eventTarget;
        this.propertyMap = new PropertyMapExtractor(eventTarget).get();
    }

    public void reRegister(LocationContext parentContext, String fallbackSimpleName) {
        LocationContext previouslyRegisteredAs = (LocationContext) propertyMap.get(LOCATION_CONTEXT_PROPERTY);
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
}
