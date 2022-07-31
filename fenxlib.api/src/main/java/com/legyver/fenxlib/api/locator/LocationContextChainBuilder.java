package com.legyver.fenxlib.api.locator;

/**
 * Builder to simplify creation of LocationContext chains
 */
public class LocationContextChainBuilder {
    private LocationContext locationContext;

    /**
     * Build the location context
     * @return the location context
     */
    public LocationContext build() {
        return locationContext;
    }

    /**
     * Specify the name of a region
     * @param region the region name
     * @return this builder
     */
    public LocationContextChainBuilder in(String region) {
        if (locationContext == null) {
            locationContext = new DefaultLocationContext(region);
        } else {
            locationContext = new LocationContextDecorator(locationContext);
            ((LocationContextDecorator) locationContext).setName(region);
        }
        return this;
    }

    /**
     * Soecify the name of a component
     * @param name the name of the component
     * @return this builder
     */
    public LocationContextChainBuilder named(String name) {
        return in(name);
    }
}
