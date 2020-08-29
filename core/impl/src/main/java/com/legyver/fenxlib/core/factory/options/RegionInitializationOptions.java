package com.legyver.fenxlib.core.factory.options;

import com.legyver.fenxlib.core.factory.RegionFactory;
import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;

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

	public static class Builder<T extends Builder> {

		private String regionLocator;
		private RegionFactory factory;

		private boolean displayContentByDefault = false;

		void setRegionLocator(String regionLocator) {
			this.regionLocator = regionLocator;
		}

		public RegionInitializationOptions build() {
			return new RegionInitializationOptions(factory, new DefaultLocationContext(regionLocator), displayContentByDefault);
		}

		public T factory(RegionFactory factory) {
			this.factory = factory;
			return (T) this;
		}

		public T displayContentByDefault() {
			displayContentByDefault = true;
			return (T) this;
		}
	}

	public static class SideBuilder extends Builder<SideBuilder> {
		private final String label;

		public SideBuilder(String label) {
			super();
			this.label = label;
		}

		String getLabel() {
			return label;
		}
	}

	public static class SideAwareBuilder extends Builder<SideAwareBuilder> {
		private String leftLabel;
		private String rightLabel;

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
