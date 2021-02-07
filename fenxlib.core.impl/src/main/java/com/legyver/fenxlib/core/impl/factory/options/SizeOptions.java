package com.legyver.fenxlib.core.impl.factory.options;

import com.legyver.fenxlib.core.impl.factory.options.SizeOptions.OrientatedBuilder.HeightOrientatedBuilder;
import com.legyver.fenxlib.core.impl.factory.options.SizeOptions.OrientatedBuilder.SideOrientatedBuilder;

public class SizeOptions {
	private final double minWidth;
	private final double prefWidth;
	private final double maxWidth;
	private final double minHeight;
	private final double prefHeight;
	private final double maxHeight;

	public SizeOptions(double minWidth, double prefWidth, double maxWidth, double minHeight, double prefHeight, double maxHeight) {
		this.minWidth = minWidth;
		this.prefWidth = prefWidth;
		this.maxWidth = maxWidth;
		this.minHeight = minHeight;
		this.prefHeight = prefHeight;
		this.maxHeight = maxHeight;
	}

	public double getMinWidth() {
		return minWidth;
	}

	public double getPrefWidth() {
		return prefWidth;
	}

	public double getMaxWidth() {
		return maxWidth;
	}

	public double getMinHeight() {
		return minHeight;
	}

	public double getPrefHeight() {
		return prefHeight;
	}

	public double getMaxHeight() {
		return maxHeight;
	}

	public static class Builder {
		private final SideOrientatedBuilder width = new SideOrientatedBuilder(this);
		private final HeightOrientatedBuilder height = new HeightOrientatedBuilder(this);

		public SideOrientatedBuilder width() {
			return width;
		}

		public HeightOrientatedBuilder height() {
			return height;
		}

		public SizeOptions build() {
			return new SizeOptions(width.min, width.pref, width.max, height.min, height.pref, height.max);
		}
	}


	public static class OrientatedBuilder<T extends OrientatedBuilder> {
		protected final Builder parent;
		protected double min;
		protected double pref;
		protected double max;

		private OrientatedBuilder(Builder parent) {
			this.parent = parent;
		}

		public SizeOptions build() {
			return parent.build();
		}

		public T min(double min) {
			this.min = min;
			return (T) this;
		}

		public T pref(double pref) {
			this.pref = pref;
			return (T) this;
		}

		public T max(double max) {
			this.max = max;
			return (T) this;
		}

		public static class SideOrientatedBuilder extends OrientatedBuilder<SideOrientatedBuilder> {

			public SideOrientatedBuilder(Builder parent) {
				super(parent);
			}

			public HeightOrientatedBuilder height() {
				return parent.height;
			}
		}

		public static class HeightOrientatedBuilder extends OrientatedBuilder<HeightOrientatedBuilder> {

			public HeightOrientatedBuilder(Builder parent) {
				super(parent);
			}

			public SideOrientatedBuilder height() {
				return parent.width;
			}
		}

	}
}
