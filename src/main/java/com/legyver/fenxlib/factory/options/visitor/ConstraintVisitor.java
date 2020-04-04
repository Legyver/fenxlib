package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.factory.options.blade.NameSelectOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.factory.options.blade.UnlabeledEditableTextOption;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import com.legyver.fenxlib.locator.LocationContext;

public class ConstraintVisitor extends AbstractGridPaneLayoutVisitor {

	public ConstraintVisitor(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		setColumnConstraints(row);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		setColumnConstraints(row);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		setColumnConstraints(row);
	}

	private void setColumnConstraints(int row) {
		if (row == 0) {
			ColumnConstraints columnConstraintLabel = new ColumnConstraints(30, 75, 200);
			columnConstraintLabel.setHgrow(Priority.ALWAYS);
			ColumnConstraints columnConstraintsFieldL = new ColumnConstraints(30, 50, 100);
			ColumnConstraints columnConstraintsFieldM = new ColumnConstraints(30, 50, 100);
			ColumnConstraints columnConstraintsFieldR = new ColumnConstraints(30, 50, 100);
			ColumnConstraints columnConstraintsFieldB = new ColumnConstraints(30, 50, 100);
			bladeContext.getWorkingGrid().getColumnConstraints().addAll(columnConstraintLabel, columnConstraintsFieldL, columnConstraintsFieldM, columnConstraintsFieldR, columnConstraintsFieldB);
		}
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		setColumnConstraints(row);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		setColumnConstraints(row);
	}

	@Override
	public void visit(UnlabeledEditableTextOption editableTextOption, int row) {
		setColumnConstraints(row);
	}

	@Override
	public void visit(NameSelectOption option, int row) {
		setColumnConstraints(row);
	}

}
