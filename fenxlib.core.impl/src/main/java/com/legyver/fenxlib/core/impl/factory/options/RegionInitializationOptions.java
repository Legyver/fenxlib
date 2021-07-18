package com.legyver.fenxlib.core.impl.factory.options;

import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.RegionFactory;

/**
 * Initialization options for the application regions based on
 * - a top pane
 * - a bottom pane
 * - hide-able left and right side-panes
 * - the main content pane
 */
public class RegionInitializationOptions {

	private final RegionFactory factory;
	private final LocationContext locationContext;
	private final boolean displayContentByDefault;

	/**
	 * Construct application region initialization options that hosts the main application layout
	 * @param factory the region factory
	 * @param locationContext the location context for the region
	 * @param displayContentByDefault flag to specify if the regions should be displayed when the application is opened
	 */
	public RegionInitializationOptions(RegionFactory factory, LocationContext locationContext, boolean displayContentByDefault) {
		this.factory = factory;
		this.locationContext = locationContext;
		this.displayContentByDefault = displayContentByDefault;
	}

	/**
	 * Get the factory for constructing the region
	 * @return the region
	 */
	public RegionFactory getFactory() {
		return factory;
	}

	/**
	 * Get the Location Context to use for the region
	 * @return the location context
	 */
	public LocationContext getLocationContext() {
		return locationContext;
	}

	/**
	 * Get the display content by default flag
	 * @return the flag
	 */
	public boolean isDisplayContentByDefault() {
		return displayContentByDefault;
	}

	/**
	 * Options used in constructing the side areas
	 */
	public static class SideOptions {

		private final boolean displaySideByDefault;
		private final String displaySideToggleText;

		/**
		 * Construct options specifying if the side panel should be displayed by default and what the show/hide control text should be
		 * @param displaySideByDefault a flag for if the side panel should be displayed by default
		 * @param displaySideToggleText the show/hide control text
		 */
		public SideOptions(boolean displaySideByDefault, String displaySideToggleText) {
			this.displaySideByDefault = displaySideByDefault;
			this.displaySideToggleText = displaySideToggleText;
		}

		/**
		 * Get if the side should be displayed by default
		 * @return the display side by default flag
		 */
		public boolean isDisplaySideByDefault() {
			return displaySideByDefault;
		}

		/**
		 * Get the show/hide control text
		 * @return the text for the show/hide control
		 */
		public String getDisplaySideToggleText() {
			return displaySideToggleText;
		}

	}

	/**
	 * Region initialization options for the region that will host the controls to show/hide the side panes
	 */
	public static class SideAwareRegionInitializationOptions extends RegionInitializationOptions {

		private final SideOptions leftOptions;
		private final SideOptions rightOptions;

		/**
		 * Construct region initialization options
		 * @param factory the factory that produces the region's content
		 * @param locationContext the location context for the region
		 * @param displayContentByDefault if the region should be displayed by default
		 * @param leftOptions left-specific options
		 * @param rightOptions right-specific options
		 */
		public SideAwareRegionInitializationOptions(RegionFactory factory, LocationContext locationContext, boolean displayContentByDefault, SideOptions leftOptions, SideOptions rightOptions) {
			super(factory, locationContext, displayContentByDefault);
			this.leftOptions = leftOptions;
			this.rightOptions = rightOptions;
		}

		/**
		 * Get the left options
		 * @return the options for the left pane
		 */
		public SideOptions getLeftOptions() {
			return leftOptions;
		}

		/**
		 * Get the right options
		 * @return the options for the right pane
		 */
		public SideOptions getRightOptions() {
			return rightOptions;
		}

	}

	/**
	 * Base Builder class
	 * @param <T> the type of the extending builder to support implicit down-casting
	 */
	public static class Builder<T extends Builder> {

		private String regionLocator;
		private RegionFactory factory;

		private boolean displayContentByDefault = false;

		void setRegionLocator(String regionLocator) {
			this.regionLocator = regionLocator;
		}

		/**
		 * Build the region initialization options
		 * @return the region initialization options
		 */
		public RegionInitializationOptions build() {
			return new RegionInitializationOptions(factory, new DefaultLocationContext(regionLocator), displayContentByDefault);
		}

		/**
		 * Specify the factory for the region
		 * @param factory the factory to use
		 * @return the sub-classing builder
		 */
		public T factory(RegionFactory factory) {
			this.factory = factory;
			return (T) this;
		}

		/**
		 * Set the display content by default flag to true
		 * @return the sub-classing builder
		 */
		public T displayContentByDefault() {
			displayContentByDefault = true;
			return (T) this;
		}
	}

	/**
	 * Builder for producing the side content
	 */
	public static class SideBuilder extends Builder<SideBuilder> {
		private final String label;

		/**
		 * Construct a Side Builder with a control labeled with the specified text to show/hide the side
		 * @param label the label for the show/hide control
		 */
		public SideBuilder(String label) {
			super();
			this.label = label;
		}

		String getLabel() {
			return label;
		}
	}

	/**
	 * Builder for producing the content of the pane that hosts the controls to show/hide the side menus
	 */
	public static class SideAwareBuilder extends Builder<SideAwareBuilder> {
		private String leftLabel;
		private String rightLabel;

		/**
		 * Construct region initialization options for the sides
		 * @param displayLeft true if the left-pane should be visible by default
		 * @param displayRight true if the right-pane should be visible by default
		 * @return the sub-classing builder
		 */
		public SideAwareRegionInitializationOptions build(boolean displayLeft, boolean displayRight) {
			return new SideAwareRegionInitializationOptions(super.factory, new DefaultLocationContext(super.regionLocator), super.displayContentByDefault, new SideOptions(displayLeft, leftLabel), new SideOptions(displayRight, rightLabel));
		}

		void setRightLabel(String name) {
			rightLabel = name;
		}

		void setLeftLabel(String name) {
			leftLabel = name;
		}
	}

}
