package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.LabelFactory;
import com.legyver.fenxlib.factory.TextFieldFactory;
import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.LabeledBladeOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.factory.options.blade.NameSelectOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.factory.options.blade.UnlabeledEditableTextOption;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.scene.control.TextField;

public class LeftItemVisitor extends AbstractGridPaneLayoutVisitor {

	public LeftItemVisitor(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption option, int row) {
		addLabel(option, row);
	}

	private void addLabel(LabeledBladeOption option, int row) {
		Label label = new LabelFactory(option.getLabel()).makeLabel();
		bladeContext.getWorkingGrid().add(label, 0, row, option.getLabelSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption option, int row) {
		addLabel(option, row);
	}

	@Override
	public void visit(NameListClickOption option, int row) {
		addLabel(option, row);
	}

	@Override
	public void visit(NameDatePickerOption option, int row) {
		addLabel(option, row);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		//noop
	}

	@Override
	public void visit(UnlabeledEditableTextOption option, int row) {
		locationContext.setName(option.getBindLabel());
		TextField text = new TextFieldFactory(true, option.getInstantiator()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(text, 0, row, 4, 1);
	}

	@Override
	public void visit(NameSelectOption option, int row) {
		addLabel(option, row);
	}
}
