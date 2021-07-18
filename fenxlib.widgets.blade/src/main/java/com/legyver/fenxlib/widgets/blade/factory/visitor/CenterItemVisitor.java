package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.fenxlib.core.impl.factory.DatePickerFactory;
import com.legyver.fenxlib.core.impl.factory.ListViewFactory;
import com.legyver.fenxlib.core.impl.factory.TextFieldFactory;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.DatePicker;

/**
 * Visitor to apply the center item to a grid-layout
 */
public class CenterItemVisitor extends AbstractGridPaneLayoutVisitor {

	/**
	 * Construct a visitor to lay out the center item in a blade form
	 * @param handleContext location context
	 * @param bladeContext blade context
	 */
	public CenterItemVisitor(LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		locationContext.setName(nameFieldButtonOption.getLabel());
		TextField text = TextFieldFactory.jfxTextField(nameFieldButtonOption.isReadOnly()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(text, nameFieldButtonOption.getLabelSpan(), row, nameFieldButtonOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		locationContext.setName(nameFieldOption.getLabel());
		TextField text = new TextFieldFactory<>(nameFieldOption.isReadOnly(), nameFieldOption.getInstantiator()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(text, nameFieldOption.getLabelSpan(), row, nameFieldOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		locationContext.setName(nameListClickOption.getLabel());
		ListView text = new ListViewFactory(nameListClickOption.isReadOnly()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(text, nameListClickOption.getLabelSpan(), row, nameListClickOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		locationContext.setName(nameDatePickerOption.getLabel());
		DatePicker datePicker = new DatePickerFactory().makeNode(locationContext);
		bladeContext.getWorkingGrid().add(datePicker, nameDatePickerOption.getLabelSpan(), row, nameDatePickerOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		//noop
	}
}
