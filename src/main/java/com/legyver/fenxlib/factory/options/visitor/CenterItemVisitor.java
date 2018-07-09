package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.DatePickerFactory;
import com.legyver.fenxlib.factory.ListViewFactory;
import com.legyver.fenxlib.factory.TextFieldFactory;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.scene.control.DatePicker;

public class CenterItemVisitor extends AbstractGridPaneLayoutVisitor {

	public CenterItemVisitor(GridPane gp, LocationContext handleContext) {
		super(gp, handleContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		context.setName(nameFieldButtonOption.getLabel());
		TextField text = new TextFieldFactory(nameFieldButtonOption.isReadOnly()).makeNode(context);
		gp.add(text, nameFieldButtonOption.getLabelSpan(), row, nameFieldButtonOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		context.setName(nameFieldOption.getLabel());
		TextField text = new TextFieldFactory(nameFieldOption.isReadOnly(), nameFieldOption.getInstantiator()).makeNode(context);
		gp.add(text, nameFieldOption.getLabelSpan(), row, nameFieldOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		context.setName(nameListClickOption.getLabel());
		ListView text = new ListViewFactory(nameListClickOption.isReadOnly()).makeNode(context);
		gp.add(text, nameListClickOption.getLabelSpan(), row, nameListClickOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		context.setName(nameDatePickerOption.getLabel());
		DatePicker datePicker = new DatePickerFactory().makeNode(context);
		gp.add(datePicker, nameDatePickerOption.getLabelSpan(), row, nameDatePickerOption.getFieldSpan(), 1);
	}
}
