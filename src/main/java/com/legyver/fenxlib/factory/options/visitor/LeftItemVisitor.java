package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.LabelFactory;
import com.legyver.fenxlib.factory.options.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.NameFieldOption;
import com.legyver.fenxlib.factory.options.NameListClickOption;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;

public class LeftItemVisitor extends AbstractGridPaneLayoutVisitor {

	public LeftItemVisitor(GridPane gp, LocationContext handleContext) {
		super(gp, handleContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		Label label = new LabelFactory(nameFieldButtonOption.getLabel()).makeLabel();
		gp.add(label, 0, row);
	}

	@Override
	public void visit(NameFieldOption nameFieldOption, int row) {
		Label label = new LabelFactory(nameFieldOption.getLabel()).makeLabel();
		gp.add(label, 0, row);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		Label label = new LabelFactory(nameListClickOption.getLabel()).makeLabel();
		gp.add(label, 0, row);
	}
}
