package com.legyver.fenxlib.core.factory.options;

import com.legyver.fenxlib.core.factory.options.RegionInitializationOptions.SideAwareRegionInitializationOptions;

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

		private RegionInitializationOptions.Builder centerOptionsBuilder = new RegionInitializationOptions.Builder();
		private RegionInitializationOptions.Builder leftOptionsBuilder = new RegionInitializationOptions.Builder();
		private RegionInitializationOptions.Builder rightOptionsBuilder = new RegionInitializationOptions.Builder();
		private RegionInitializationOptions.SideAwareBuilder bottomOptionsBuilder = new RegionInitializationOptions.SideAwareBuilder();
		private RegionInitializationOptions.Builder topOptionsBuilder = new RegionInitializationOptions.Builder();
		private String leftLabel;
		private String rightLabel;

		public BorderPaneInitializationOptions build() {
			//regionLocator must be set before calling build() on any of these region builders
			centerOptionsBuilder.setRegionLocator(REGION_CENTER);
			leftOptionsBuilder.setRegionLocator(REGION_LEFT);
			rightOptionsBuilder.setRegionLocator(REGION_RIGHT);
			bottomOptionsBuilder.setRegionLocator(REGION_BOTTOM);
			topOptionsBuilder.setRegionLocator(REGION_TOP);
			//likewise we need to set the labels because we can't guarantee the order of the builders passed in
			bottomOptionsBuilder.setLeftLabel(leftLabel);
			bottomOptionsBuilder.setRightLabel(rightLabel);

			RegionInitializationOptions leftOptions = leftOptionsBuilder.build();
			RegionInitializationOptions rightOptions = rightOptionsBuilder.build();
			SideAwareRegionInitializationOptions bottomOptions = bottomOptionsBuilder.build(leftOptions.isDisplayContentByDefault(), rightOptions.isDisplayContentByDefault());
			return new BorderPaneInitializationOptions(centerOptionsBuilder.build(), leftOptions, rightOptions, bottomOptions, topOptionsBuilder.build());
		}

		public Builder center(RegionInitializationOptions.Builder centerOptionsBuilder) {
			this.centerOptionsBuilder = centerOptionsBuilder;
			return this;
		}

		public Builder top(RegionInitializationOptions.Builder topOptionsBuilder) {
			this.topOptionsBuilder = topOptionsBuilder;
			return this;
		}

		public Builder bottom(RegionInitializationOptions.SideAwareBuilder bottomOptionsBuilder) {
			this.bottomOptionsBuilder = bottomOptionsBuilder;
			return this;
		}

		public Builder right(RegionInitializationOptions.SideBuilder rightOptionsBuilder) {
			this.rightOptionsBuilder = rightOptionsBuilder;
			this.rightLabel = rightOptionsBuilder.getLabel();
			return this;
		}

		public Builder left(RegionInitializationOptions.SideBuilder leftOptionsBuilder) {
			this.leftOptionsBuilder = leftOptionsBuilder;
			this.leftLabel = leftOptionsBuilder.getLabel();
			return this;
		}
	}

}
