package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.Region;

/**
 * Factory to create a {@link Region}
 */
public class RegionFactory implements NodeFactory<Region> {
    @Override
    public Region makeNode(LocationContext locationContext) throws CoreException {
        Region region = makeRegion();
        Fenxlib.register(locationContext, region);
        return region;
    }

    /**
     * Factory method to instantiate a plain JavaFX Region.
     * @return a JavaFX Region, override if you want something else
     */
    protected Region makeRegion() {
        return new Region();
    }
}
