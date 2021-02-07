package com.legyver.fenxlib.widgets.blade.factory;

import com.legyver.fenxlib.widgets.blade.MoreField;
import javafx.scene.layout.GridPane;

/**
 * Context containing common blade information such as:
 *   1. Layout grid
 *   2. A show/hide field
 *   3. Additional grid to show/hide
 */
public class BladeContext {
	private GridPane workingGrid;
	private MoreField moreTrigger;
	private GridPane moreGrid = new GridPane();

	/**
	 * Construct the blade context around a specific grid
	 * @param workingGrid the grid
	 */
	public BladeContext(GridPane workingGrid) {
		this.workingGrid = workingGrid;
	}

	/**
	 * Get the main grid
	 * @return the grid
	 */
	public GridPane getWorkingGrid() {
		return workingGrid;
	}

	/**
	 * Set the field that triggers the hiding/showing of the additional fields
	 * @param moreField the field that acts as the trigger to hide/show fields
	 */
	public void setMoreTrigger(MoreField moreField) {
		moreTrigger = moreField;
		moreGrid.getRowConstraints().addAll(workingGrid.getRowConstraints());
		moreGrid.getColumnConstraints().addAll(workingGrid.getColumnConstraints());
		workingGrid = moreGrid;
	}

	/**
	 * Get the label that when clicked hides/shows the additional fields
	 * @return the "more" label
	 */
	public MoreField getMoreTrigger() {
		return moreTrigger;
	}

	/**
	 * Get the grid for laying out the "more" fields
	 * @return the grid containing the hidden fields
	 */
	public GridPane getMoreGrid() {
		return moreGrid;
	}

}
