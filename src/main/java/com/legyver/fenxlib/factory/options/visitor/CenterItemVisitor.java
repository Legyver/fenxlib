package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.fenxlib.factory.ListViewFactory;
import com.legyver.fenxlib.factory.TextFieldFactory;
import com.legyver.fenxlib.factory.options.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.NameFieldOption;
import com.legyver.fenxlib.factory.options.NameListClickOption;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;

public class CenterItemVisitor extends AbstractGridPaneLayoutVisitor {

	public CenterItemVisitor(GridPane gp, LocationContext handleContext) {
		super(gp, handleContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) {
		context.setName(nameFieldButtonOption.getLabel());
		TextField text = new TextFieldFactory(nameFieldButtonOption.isReadOnly()).makeNode(context);
		gp.add(text, 1, row, 3, 1);
	}

	@Override
	public void visit(NameFieldOption nameFieldOption, int row) {
		context.setName(nameFieldOption.getLabel());
		TextField text = new TextFieldFactory(nameFieldOption.isReadOnly()).makeNode(context);
		gp.add(text, 1, row, 4, 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		context.setName(nameListClickOption.getLabel());
		ListView text = new ListViewFactory(nameListClickOption.isReadOnly()).makeNode(context);
		gp.add(text, 1, row, 4, 1);
	}
}
