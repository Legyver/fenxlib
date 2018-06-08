package com.legyver.fenxlib.factory.options;

public abstract class OrientatedSizeOption {

	private final double min;
	private final double pref;
	private final double max;

	public OrientatedSizeOption(double min, double pref, double max) {
		this.min = min;
		this.pref = pref;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public double getPref() {
		return pref;
	}

	public double getMax() {
		return max;
	}

}
