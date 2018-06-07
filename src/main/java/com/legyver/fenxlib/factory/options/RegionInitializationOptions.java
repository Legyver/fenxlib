package com.legyver.fenxlib.factory.options;

import com.legyver.fenxlib.factory.RegionFactory;
import com.legyver.fenxlib.locator.DefaultLocationContext;
import com.legyver.fenxlib.locator.LocationContext;

public class RegionInitializationOptions {

	private final RegionFactory factory;
	private final LocationContext locationContext;
	private final boolean displayContentByDefault;

	public RegionInitializationOptions(RegionFactory factory, LocationContext locationContext, boolean displayContentByDefault) {
		this.factory = factory;
		this.locationContext = locationContext;
		this.displayContentByDefault = displayContentByDefault;
	}

	public RegionFactory getFactory() {
		return factory;
	}

	public LocationContext getLocationContext() {
		return locationContext;
	}

	public boolean isDisplayContentByDefault() {
		return displayContentByDefault;
	}

	public static class SideOptions {

		private final boolean displaySideByDefault;
		private final String displaySideToggleText;

		public SideOptions(boolean displaySideByDefault, String displaySideToggleText) {
			this.displaySideByDefault = displaySideByDefault;
			this.displaySideToggleText = displaySideToggleText;
		}

		public boolean isDisplaySideByDefault() {
			return displaySideByDefault;
		}

		public String getDisplaySideToggleText() {
			return displaySideToggleText;
		}

	}
	public static class SideAwareRegionInitializationOptions extends RegionInitializationOptions {

		private final SideOptions leftOptions;
		private final SideOptions rightOptions;

		public SideAwareRegionInitializationOptions(RegionFactory factory, LocationContext locationContext, boolean displayContentByDefault, SideOptions leftOptions, SideOptions rightOptions) {
			super(factory, locationContext, displayContentByDefault);
			this.leftOptions = leftOptions;
			this.rightOptions = rightOptions;
		}

		public SideOptions getLeftOptions() {
			return leftOptions;
		}

		public SideOptions getRightOptions() {
			return rightOptions;
		}

	}

	public static class Builder<T> {

		private final T parentBuilder;
		private final String regionLocator;
		private RegionFactory factory;

		private boolean displayContentByDefault = false;

		public Builder(T parent, String regionLocator) {
			this.parentBuilder = parent;
			this.regionLocator = regionLocator;
		}

		public T up() {
			return parentBuilder;
		}

		public RegionInitializationOptions build() {
			return new RegionInitializationOptions(factory, new DefaultLocationContext(regionLocator), displayContentByDefault);
		}

		public Builder<T> factory(RegionFactory factory) {
			this.factory = factory;
			return this;
		}

		public Builder<T> displayContentByDefault() {
			displayContentByDefault = true;
			return this;
		}
	}

	static class SideAwareBuilder<T> extends Builder<T> {
		private String leftLabel;
		private String rightLabel;

		public SideAwareBuilder(T parent, String regionLocator) {
			super(parent, regionLocator);
		}

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
