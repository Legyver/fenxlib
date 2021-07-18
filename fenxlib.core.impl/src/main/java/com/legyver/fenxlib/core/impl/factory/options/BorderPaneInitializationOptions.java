package com.legyver.fenxlib.core.impl.factory.options;

/**
 * Options describing the default layout of a fenxlib application.
 * This layout corresponds to the five sections of a BorderPane
 */
public class BorderPaneInitializationOptions {

	/**
	 * The name of the top region of the application layout
	 */
	public static final String REGION_TOP = "top";
	/**
	 * The name of the bottom region of the application layout
	 */
	public static final String REGION_BOTTOM = "bottom";
	/**
	 * The name of the left region of the application layout
	 */
	public static final String REGION_LEFT = "left";
	/**
	 * The name of the right region of the application layout
	 */
	public static final String REGION_RIGHT = "right";
	/**
	 * The name of the center region of the application layout
	 */
	public static final String REGION_CENTER = "center";

	private final RegionInitializationOptions centerOptions;
	private final RegionInitializationOptions leftOptions;
	private final RegionInitializationOptions rightOptions;
	private final RegionInitializationOptions.SideAwareRegionInitializationOptions bottomOptions;
	private final RegionInitializationOptions topOptions;

	/**
	 * Create the initialization options for the application border pane layout
	 * @param centerOptions options for the center region
	 * @param leftOptions options for the left region
	 * @param rightOptions options for the right region
	 * @param bottomOptions options for the bottom region
	 * @param topOptions options for the top region
	 */
	public BorderPaneInitializationOptions(RegionInitializationOptions centerOptions, RegionInitializationOptions leftOptions, RegionInitializationOptions rightOptions, RegionInitializationOptions.SideAwareRegionInitializationOptions bottomOptions, RegionInitializationOptions topOptions) {
		this.centerOptions = centerOptions;
		this.leftOptions = leftOptions;
		this.rightOptions = rightOptions;
		this.bottomOptions = bottomOptions;
		this.topOptions = topOptions;
	}

	/**
	 * Get the initialization options for the center region
	 * @return the initialization options for the center region
	 */
	public RegionInitializationOptions getCenterOptions() {
		return centerOptions;
	}

	/**
	 * Get the initialization options for the left region
	 * @return the initialization options for the left region
	 */
	public RegionInitializationOptions getLeftOptions() {
		return leftOptions;
	}

	/**
	 * Get the initialization options for the right region
	 * @return the initialization options for the right region
	 */
	public RegionInitializationOptions getRightOptions() {
		return rightOptions;
	}

	/**
	 * Get the initialization options for the bottom region
	 * @return the initialization options for the bottom region
	 */
	public RegionInitializationOptions.SideAwareRegionInitializationOptions getBottomOptions() {
		return bottomOptions;
	}

	/**
	 * Get the initialization options for the top region
	 * @return the initialization options for the top region
	 */
	public RegionInitializationOptions getTopOptions() {
		return topOptions;
	}

	/**
	 * Builder for creating BorderPaneInitializationOptions
	 */
	public static class Builder {

		private RegionInitializationOptions.Builder centerOptionsBuilder = new RegionInitializationOptions.Builder();
		private RegionInitializationOptions.Builder leftOptionsBuilder = new RegionInitializationOptions.Builder();
		private RegionInitializationOptions.Builder rightOptionsBuilder = new RegionInitializationOptions.Builder();
		private RegionInitializationOptions.SideAwareBuilder bottomOptionsBuilder = new RegionInitializationOptions.SideAwareBuilder();
		private RegionInitializationOptions.Builder topOptionsBuilder = new RegionInitializationOptions.Builder();
		private String leftLabel;
		private String rightLabel;

		/**
		 * Build the BorderPane initialization options
		 * @return the options for the border pane layout
		 */
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
			RegionInitializationOptions.SideAwareRegionInitializationOptions bottomOptions = bottomOptionsBuilder.build(leftOptions.isDisplayContentByDefault(), rightOptions.isDisplayContentByDefault());
			return new BorderPaneInitializationOptions(centerOptionsBuilder.build(), leftOptions, rightOptions, bottomOptions, topOptionsBuilder.build());
		}

		/**
		 * Specify the builder to use when specifying options for the center region
		 * @param centerOptionsBuilder the builder to use
		 * @return this builder
		 */
		public Builder center(RegionInitializationOptions.Builder centerOptionsBuilder) {
			this.centerOptionsBuilder = centerOptionsBuilder;
			return this;
		}

		/**
		 * Specify the builder to use when specifying options for the top region
		 * @param topOptionsBuilder the builder to use
		 * @return this builder
		 */
		public Builder top(RegionInitializationOptions.Builder topOptionsBuilder) {
			this.topOptionsBuilder = topOptionsBuilder;
			return this;
		}

		/**
		 * Specify the builder to use when specifying options for the bottom region
		 * @param bottomOptionsBuilder the builder to use
		 * @return this builder
		 */
		public Builder bottom(RegionInitializationOptions.SideAwareBuilder bottomOptionsBuilder) {
			this.bottomOptionsBuilder = bottomOptionsBuilder;
			return this;
		}

		/**
		 * Specify the builder to use when specifying options for the right panel
		 * @param rightOptionsBuilder the builder to use
		 * @return this builder
		 */
		public Builder right(RegionInitializationOptions.SideBuilder rightOptionsBuilder) {
			this.rightOptionsBuilder = rightOptionsBuilder;
			this.rightLabel = rightOptionsBuilder.getLabel();
			return this;
		}

		/**
		 * Specify the builder to use when specifying options for the left panel
		 * @param leftOptionsBuilder the builder to use
		 * @return this builder
		 */
		public Builder left(RegionInitializationOptions.SideBuilder leftOptionsBuilder) {
			this.leftOptionsBuilder = leftOptionsBuilder;
			this.leftLabel = leftOptionsBuilder.getLabel();
			return this;
		}
	}

}
