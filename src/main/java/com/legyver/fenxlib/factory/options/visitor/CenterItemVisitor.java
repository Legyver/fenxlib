package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.DatePickerFactory;
import com.legyver.fenxlib.factory.JFXComboBoxFactory;
import com.legyver.fenxlib.factory.ListViewFactory;
import com.legyver.fenxlib.factory.TextFieldFactory;
import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import com.legyver.fenxlib.factory.options.blade.NameSelectOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.factory.options.blade.UnlabeledEditableTextOption;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class CenterItemVisitor extends AbstractGridPaneLayoutVisitor {

	public CenterItemVisitor(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		locationContext.setName(nameFieldButtonOption.getLabel());
		TextField text = new TextFieldFactory(nameFieldButtonOption.isReadOnly()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(text, nameFieldButtonOption.getLabelSpan(), row, nameFieldButtonOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		locationContext.setName(nameFieldOption.getLabel());
		TextField text = new TextFieldFactory(nameFieldOption.isReadOnly(), nameFieldOption.getInstantiator()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(text, nameFieldOption.getLabelSpan(), row, nameFieldOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		locationContext.setName(nameListClickOption.getLabel());
		ListView list = new ListViewFactory(nameListClickOption.isReadOnly()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(list, nameListClickOption.getLabelSpan(), row, nameListClickOption.getFieldSpan(), 1);
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

	@Override
	public void visit(UnlabeledEditableTextOption editableTextOption, int row) {
		///noop
	}

	@Override
	public void visit(NameSelectOption option, int row) {
		locationContext.setName(option.getLabel());
		ComboBox combo = new JFXComboBoxFactory(option.getItems()).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(combo, option.getLabelSpan(), row, option.getFieldSpan(), 1);
	}
}
