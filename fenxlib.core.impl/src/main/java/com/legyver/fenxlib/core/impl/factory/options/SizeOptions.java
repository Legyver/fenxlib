package com.legyver.fenxlib.core.impl.factory.options;

import com.legyver.fenxlib.core.impl.factory.options.SizeOptions.OrientatedBuilder.HeightOrientatedBuilder;
import com.legyver.fenxlib.core.impl.factory.options.SizeOptions.OrientatedBuilder.WidthOrientatedBuilder;

/**
 * Size options that too apply to Regions
 */
public class SizeOptions {
	/**
	 * The minimum width
	 */
	private final double minWidth;
	/**
	 * The preferred width
	 */
	private final double prefWidth;
	/**
	 * The maximum width
	 */
	private final double maxWidth;
	/**
	 * The minimum height
	 */
	private final double minHeight;
	/**
	 * The preferred height
	 */
	private final double prefHeight;
	/**
	 * The maximum height
	 */
	private final double maxHeight;

	/**
	 * Construct size options with the specified minimums, maximums, and preferences
	 * @param minWidth the minimum width
	 * @param prefWidth the preferred width
	 * @param maxWidth the max width
	 * @param minHeight the minimum height
	 * @param prefHeight the preferred height
	 * @param maxHeight the maximum height
	 */
	public SizeOptions(double minWidth, double prefWidth, double maxWidth, double minHeight, double prefHeight, double maxHeight) {
		this.minWidth = minWidth;
		this.prefWidth = prefWidth;
		this.maxWidth = maxWidth;
		this.minHeight = minHeight;
		this.prefHeight = prefHeight;
		this.maxHeight = maxHeight;
	}

	/**
	 * Retrieve the minimum width
	 * @return the minimum width
	 */
	public double getMinWidth() {
		return minWidth;
	}

	/**
	 * Retrieve the preferred width
	 * @return the preferred width
	 */
	public double getPrefWidth() {
		return prefWidth;
	}

	/**
	 * Retrieve the maximum width
	 * @return the maximum width
	 */
	public double getMaxWidth() {
		return maxWidth;
	}

	/**
	 * Retrieve the minimum height
	 * @return the minimum height
	 */
	public double getMinHeight() {
		return minHeight;
	}

	/**
	 * Retrieve the preferred height
	 * @return the preferred height
	 */
	public double getPrefHeight() {
		return prefHeight;
	}

	/**
	 * Retrieve the maximum height
	 * @return the maximum height
	 */
	public double getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Builder for creating SizeOptions
	 */
	public static class Builder {
		private final WidthOrientatedBuilder width = new WidthOrientatedBuilder(this);
		private final HeightOrientatedBuilder height = new HeightOrientatedBuilder(this);

		/**
		 * Shortcut to the width builder
		 * @return the width builder
		 */
		public WidthOrientatedBuilder width() {
			return width;
		}

		/**
		 * Shortcut to the height builder
		 * @return the height builder
		 */
		public HeightOrientatedBuilder height() {
			return height;
		}

		/**
		 * Build the size options
		 * @return the size options
		 */
		public SizeOptions build() {
			return new SizeOptions(width.min, width.pref, width.max, height.min, height.pref, height.max);
		}
	}


	/**
	 * Base builder for building min-max-pref values for a given dimension (height or width)
	 * @param <T> the type of the sub-classing builder to support down-casting
	 */
	public static class OrientatedBuilder<T extends OrientatedBuilder> {
		/**
		 * The parent builder.  Used for short-cutting between builders
		 */
		protected final Builder parent;
		/**
		 * The minimum value accepted for the dimension
		 */
		protected double min;
		/**
		 * The default value preferred for the dimension
		 */
		protected double pref;
		/**
		 * The maximum value accepted for the dimension
		 */
		protected double max;

		private OrientatedBuilder(Builder parent) {
			this.parent = parent;
		}

		/**
		 * The size options
		 * @return the built size options reference
		 */
		public SizeOptions build() {
			return parent.build();
		}

		/**
		 * The minimum value to use
		 * @param min the minimum value
		 * @return the sub-classing builder
		 */
		public T min(double min) {
			this.min = min;
			return (T) this;
		}

		/**
		 * The preferred value to use
		 * @param pref the preferred value
		 * @return the sub-classing builder
		 */
		public T pref(double pref) {
			this.pref = pref;
			return (T) this;
		}

		/**
		 * The max to use
		 * @param max the maximum accepted value
		 * @return the sub-classing builder
		 */
		public T max(double max) {
			this.max = max;
			return (T) this;
		}

		/**
		 * Builder to construct width options
		 */
		public static class WidthOrientatedBuilder extends OrientatedBuilder<WidthOrientatedBuilder> {

			/**
			 * Construct a builder for width options
			 * @param parent the parent builder
			 */
			public WidthOrientatedBuilder(Builder parent) {
				super(parent);
			}

			/**
			 * Get a reference to the height builder
			 * @return the height builder reference
			 */
			public HeightOrientatedBuilder height() {
				return parent.height;
			}
		}

		/**
		 * Builder to construct height options
		 */
		public static class HeightOrientatedBuilder extends OrientatedBuilder<HeightOrientatedBuilder> {

			/**
			 * Construct a builder to specify height dimensions
			 * @param parent the parent builder
			 */
			public HeightOrientatedBuilder(Builder parent) {
				super(parent);
			}

			/**
			 * Get a reference to the width builder
			 * @return the width builder
			 */
			public WidthOrientatedBuilder width() {
				return parent.width;
			}
		}

	}
}
