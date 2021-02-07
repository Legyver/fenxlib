package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.options.RegionInitializationOptions;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public interface RegionFactory<T extends Region, U extends RegionInitializationOptions> {

	T makeRegion(BorderPane borderPane, U regionInitOptions) throws CoreException;

}
