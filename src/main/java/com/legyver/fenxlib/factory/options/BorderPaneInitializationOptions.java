package com.legyver.fenxlib.factory.options;

import com.legyver.fenxlib.factory.options.RegionInitializationOptions.SideAwareRegionInitializationOptions;

public class BorderPaneInitializationOptions {

	public static final String REGION_TOP = "top";
	public static final String REGION_BOTTOM = "bottom";
	public static final String REGION_LEFT = "left";
	public static final String REGION_RIGHT = "right";
	public static final String REGION_CENTER = "center";

	private final RegionInitializationOptions centerOptions;
	private final RegionInitializationOptions leftOptions;
	private final RegionInitializationOptions rightOptions;
	private final SideAwareRegionInitializationOptions bottomOptions;
	private final RegionInitializationOptions topOptions;

	public BorderPaneInitializationOptions(RegionInitializationOptions centerOptions, RegionInitializationOptions leftOptions, RegionInitializationOptions rightOptions, SideAwareRegionInitializationOptions bottomOptions, RegionInitializationOptions topOptions) {
		this.centerOptions = centerOptions;
		this.leftOptions = leftOptions;
		this.rightOptions = rightOptions;
		this.bottomOptions = bottomOptions;
		this.topOptions = topOptions;
	}

	public RegionInitializationOptions getCenterOptions() {
		return centerOptions;
	}

	public RegionInitializationOptions getLeftOptions() {
		return leftOptions;
	}

	public RegionInitializationOptions getRightOptions() {
		return rightOptions;
	}

	public SideAwareRegionInitializationOptions getBottomOptions() {
		return bottomOptions;
	}

	public RegionInitializationOptions getTopOptions() {
		return topOptions;
	}

	public static class Builder {

		private final RegionInitializationOptions.Builder<Builder> centerOptionsBuilder = new RegionInitializationOptions.Builder<>(this, REGION_CENTER);
		private final RegionInitializationOptions.Builder<Builder> leftOptionsBuilder = new RegionInitializationOptions.Builder<>(this, REGION_LEFT);
		private final RegionInitializationOptions.Builder<Builder> rightOptionsBuilder = new RegionInitializationOptions.Builder<>(this, REGION_RIGHT);
		private final RegionInitializationOptions.SideAwareBuilder<Builder> bottomOptionsBuilder = new RegionInitializationOptions.SideAwareBuilder<>(this, REGION_BOTTOM);
		private final RegionInitializationOptions.Builder<Builder> topOptionsBuilder = new RegionInitializationOptions.Builder<>(this, REGION_TOP);


		public BorderPaneInitializationOptions build() {
			RegionInitializationOptions leftOptions = leftOptionsBuilder.build();
			RegionInitializationOptions rightOptions = rightOptionsBuilder.build();
			SideAwareRegionInitializationOptions bottomOptions = bottomOptionsBuilder.build(leftOptions.isDisplayContentByDefault(), rightOptions.isDisplayContentByDefault());
			return new BorderPaneInitializationOptions(centerOptionsBuilder.build(), leftOptions, rightOptions, bottomOptions, topOptionsBuilder.build());
		}

		public RegionInitializationOptions.Builder<Builder> center() {
			return centerOptionsBuilder;
		}

		public RegionInitializationOptions.Builder<Builder> top() {
			return topOptionsBuilder;
		}

		public RegionInitializationOptions.Builder<Builder> bottom() {
			return bottomOptionsBuilder;
		}

		public RegionInitializationOptions.Builder<Builder> right(String name) {
			bottomOptionsBuilder.setRightLabel(name);
			return rightOptionsBuilder;
		}

		public RegionInitializationOptions.Builder<Builder> left(String name) {
			bottomOptionsBuilder.setLeftLabel(name);
			return leftOptionsBuilder;
		}
	}

}
