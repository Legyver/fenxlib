package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.NameFieldOption;
import com.legyver.fenxlib.factory.options.NameListClickOption;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;

public abstract class AbstractGridPaneLayoutVisitor {
	protected final GridPane gp;
	protected final LocationContext context;

	public AbstractGridPaneLayoutVisitor(GridPane gp, LocationContext context) {
		this.gp = gp;
		this.context = context;
	}

	public abstract void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException;
	public abstract void visit(NameFieldOption nameFieldOption, int row);
	public abstract void visit(NameListClickOption nameListClickOption, int row);
}
