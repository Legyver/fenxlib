package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import com.legyver.fenxlib.core.api.locator.LocationContext;

/**
 * Visitor to apply the column constraints to a grid-layout
 */
public class ConstraintVisitor extends AbstractGridPaneLayoutVisitor {

	/**
	 * Construct a visitor to apply the column constriants
	 * @param handleContext the location context to use
	 * @param bladeContext the location context to use
	 */
	public ConstraintVisitor(LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	/**
	 * Apply the column constraints to a nameFieldButtonOption
	 * @param nameFieldButtonOption the option to apply the constraints to
	 * @param row the row to apply the constraints to
	 */
	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		setColumnConstraints(row);
	}

	/**
	 * Apply the column constraints to a nameFieldOption
	 * @param nameFieldOption the option to apply the constraints to
	 * @param row the row to apply the constraints to
	 */
	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		setColumnConstraints(row);
	}

	/**
	 * Apply the column constraints to a nameListClickOption
	 * @param nameListClickOption the option to apply the constraints to
	 * @param row the row to apply the constraints to
	 */
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

	/**
	 * Apply the column constraints to a nameDatePickerOption
	 * @param nameDatePickerOption the option to apply the constraints to
	 * @param row the row to apply the constraints to
	 */
	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		setColumnConstraints(row);
	}

	/**
	 * Apply the column constraints to a showMoreOptions
	 * @param showMoreOptions the option to apply the constraints to
	 * @param row the row to apply the constraints to
	 */
	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		setColumnConstraints(row);
	}
}
