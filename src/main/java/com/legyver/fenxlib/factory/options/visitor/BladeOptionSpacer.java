package com.legyver.fenxlib.factory.options.visitor;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.legyver.fenxlib.factory.options.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.NameFieldOption;
import com.legyver.fenxlib.factory.options.NameListClickOption;
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
	public void visit(NameFieldOption nameFieldOption, int row) {
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

}
