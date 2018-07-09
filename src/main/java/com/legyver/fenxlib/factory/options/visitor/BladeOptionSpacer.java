package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.locator.LocationContext;

public class BladeOptionSpacer extends AbstractGridPaneLayoutVisitor {

	public BladeOptionSpacer(GridPane gp, LocationContext handleContext) {
		super(gp, handleContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		gp.add(getSpacer(), row, 3);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		gp.add(getSpacer(), row, 3);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		gp.add(getSpacer(), row, 3);
	}

	private Region getSpacer() {
		Region spacer = new Region();
		spacer.setMinSize(10, 36);
		return spacer;
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		gp.add(getSpacer(), row, 3);
	}

}
