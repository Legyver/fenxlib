package com.legyver.fenxlib.core.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.layout.options.RegionInitializationOptions;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

/**
 * Factory for creating regions of the main application borderpane layout should implement this
 * @param <T> the type of the region to be created
 * @param <U> the type of the region initialization options for the region
 */
public interface RegionFactory<T extends Region, U extends RegionInitializationOptions> {

	/**
	 * Construct a region.
	 * The main application border pane is only passed in to allow for the side-pane factories to add their controls to the bottom pane factory's parameters
	 * @param borderPane the main application border pane
	 * @param regionInitOptions the initialization options for the region
	 * @return the created region.
	 * @throws CoreException if any of the nested builders throw an exception
	 */
	T makeRegion(BorderPane borderPane, U regionInitOptions) throws CoreException;

}
