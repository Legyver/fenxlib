package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.factory.options.blade.NameSelectOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.factory.options.blade.UnlabeledEditableTextOption;
import com.legyver.fenxlib.locator.LocationContext;

public class BladeOptionSpacer extends AbstractGridPaneLayoutVisitor {

	public BladeOptionSpacer(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		addSpacer(row);
	}

	private void addSpacer(int row) {
		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		addSpacer(row);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		addSpacer(row);
	}

	private Region getSpacer() {
		Region spacer = new Region();
		spacer.setMinSize(10, 36);
		return spacer;
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		addSpacer(row);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		addSpacer(row);
	}

	@Override
	public void visit(UnlabeledEditableTextOption editableTextOption, int row) {
//		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

	@Override
	public void visit(NameSelectOption option, int row) {
		addSpacer(row);
	}

}
