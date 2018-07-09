package com.legyver.fenxlib.factory.options.blade;

public class AbstractBladeGridLayout {
	protected final static int GRID_COLS = 5;
	protected final int labelSpan;
	protected final int fieldSpan;

	public AbstractBladeGridLayout(int labelSpan, int fieldSpan) {
		this.labelSpan = labelSpan;
		this.fieldSpan = fieldSpan;
	}

	public int getLabelSpan() {
		return labelSpan;
	}

	public int getFieldSpan() {
		return fieldSpan;
	}

}
