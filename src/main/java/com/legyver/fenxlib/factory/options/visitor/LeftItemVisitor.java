package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.LabelFactory;
import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;

public class LeftItemVisitor extends AbstractGridPaneLayoutVisitor {

	public LeftItemVisitor(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		Label label = new LabelFactory(nameFieldButtonOption.getLabel()).makeLabel();
		bladeContext.getWorkingGrid().add(label, 0, row, nameFieldButtonOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {		
		Label label = new LabelFactory(nameFieldOption.getLabel()).makeLabel();
		bladeContext.getWorkingGrid().add(label, 0, row, nameFieldOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		Label label = new LabelFactory(nameListClickOption.getLabel()).makeLabel();
		bladeContext.getWorkingGrid().add(label, 0, row, nameListClickOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		Label label = new LabelFactory(nameDatePickerOption.getLabel()).makeLabel();
		bladeContext.getWorkingGrid().add(label, 0, row, nameDatePickerOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		//noop
	}
}
