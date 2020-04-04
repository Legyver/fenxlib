package com.legyver.fenxlib.factory.context;

import com.legyver.fenxlib.widget.MoreField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BladeContext {

	private GridPane workingGrid;
	private MoreField moreTrigger;
	private TextField editableField;
	private GridPane moreGrid = new GridPane();

	public BladeContext(GridPane workingGrid) {
		this.workingGrid = workingGrid;
	}

	public GridPane getWorkingGrid() {
		return workingGrid;
	}

	public void setMoreTrigger(MoreField node) {
		moreTrigger = node;
		moreGrid.getRowConstraints().addAll(workingGrid.getRowConstraints());
		moreGrid.getColumnConstraints().addAll(workingGrid.getColumnConstraints());
		workingGrid = moreGrid;
	}

	public void setEditableField(TextField editableField) {
		this.editableField = editableField;
	}

	public TextField getEditableField() {
		return editableField;
	}

	public MoreField getMoreTrigger() {
		return moreTrigger;
	}

	public GridPane getMoreGrid() {
		return moreGrid;
	}

}
