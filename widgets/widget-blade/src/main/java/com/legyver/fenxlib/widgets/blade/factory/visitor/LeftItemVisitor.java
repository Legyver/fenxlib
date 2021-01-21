package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.fenxlib.core.impl.factory.LabelFactory;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import javafx.scene.control.Label;
import com.legyver.fenxlib.core.api.locator.LocationContext;

/**
 * Visitor to apply the left item to a grid-layout
 */
public class LeftItemVisitor extends AbstractGridPaneLayoutVisitor {

	/**
	 * Constructor for a visitor
	 * @param handleContext the location context to use
	 * @param bladeContext the blade context to use
	 */
	public LeftItemVisitor(LocationContext handleContext, BladeContext bladeContext) {
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
