package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public interface RegionFactory<T extends Region, U extends RegionInitializationOptions> {

	T makeRegion(BorderPane borderPane, U regionInitOptions) throws CoreException;

}
