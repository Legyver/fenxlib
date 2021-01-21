package com.legyver.fenxlib.widgets.blade.factory.blade;

/**
 * Abstract layout grid to use for the blades
 */
public class AbstractBladeGridLayout {
	/**
	 * Default number of columns in a grid
	 */
	protected final static int GRID_COLS = 5;
	private final int labelSpan;
	private final int fieldSpan;

	/**
	 * Base layout for a blade grid
	 * @param labelSpan the number of columns the label spans
	 * @param fieldSpan the number of columns the field spans
	 */
	public AbstractBladeGridLayout(int labelSpan, int fieldSpan) {
		this.labelSpan = labelSpan;
		this.fieldSpan = fieldSpan;
	}

	/**
	 * Get the number of columns the label spans
	 * @return the label span
	 */
	public int getLabelSpan() {
		return labelSpan;
	}

	/**
	 * Get the number of columns the field spans
	 * @return the field span
	 */
	public int getFieldSpan() {
		return fieldSpan;
	}

}
