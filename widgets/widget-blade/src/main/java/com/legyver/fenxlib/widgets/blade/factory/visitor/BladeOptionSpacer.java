package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.core.locator.LocationContext;

public class BladeOptionSpacer extends AbstractGridPaneLayoutVisitor {

	public BladeOptionSpacer(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

	private Region getSpacer() {
		Region spacer = new Region();
		spacer.setMinSize(10, 36);
		return spacer;
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		bladeContext.getWorkingGrid().add(getSpacer(), row, 3);
	}

}
