package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.LabelFactory;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
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
		gp.add(label, 0, row, nameFieldButtonOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		Label label = new LabelFactory(nameFieldOption.getLabel()).makeLabel();
		gp.add(label, 0, row, nameFieldOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		Label label = new LabelFactory(nameListClickOption.getLabel()).makeLabel();
		gp.add(label, 0, row, nameListClickOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		Label label = new LabelFactory(nameDatePickerOption.getLabel()).makeLabel();
		gp.add(label, 0, row, nameDatePickerOption.getLabelSpan(), 1);
	}
}
