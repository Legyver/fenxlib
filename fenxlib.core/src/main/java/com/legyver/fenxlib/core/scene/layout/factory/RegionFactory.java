package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.scene.layout.options.RegionOptions;
import javafx.scene.layout.Region;

/**
 * Factory to create a {@link Region}
 */
public class RegionFactory implements NodeFactory<Region, RegionOptions> {

    @Override
    public Region makeNode(LocationContext locationContext, RegionOptions options) throws CoreException {
        Region region = makeRegion();
        Fenxlib.register(locationContext.decorateWith(options.getName()), region);
        return region;
    }

    @Override
    public RegionOptions newOptions() {
        return new RegionOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX Region.
     * @return a JavaFX Region, override if you want something else
     */
    protected Region makeRegion() {
        return new Region();
    }
}
