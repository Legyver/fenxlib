package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.core.exception.CoreException;
import javafx.scene.control.Button;
import com.legyver.fenxlib.factory.SvgIconFactory;
import com.legyver.fenxlib.factory.options.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.NameFieldOption;
import com.legyver.fenxlib.factory.options.NameListClickOption;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.factory.decorator.ButtonIconDecorator;
import com.legyver.fenxlib.factory.decorator.ButtonTooltipDecorator;
import com.legyver.fenxlib.factory.options.TooltipIconOptions;
import com.legyver.fenxlib.locator.LocationContext;

public class RightItemVisitor extends AbstractGridPaneLayoutVisitor {

	public RightItemVisitor(GridPane gp, LocationContext handleContext) {
		super(gp, handleContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException {
		TooltipIconOptions options = nameFieldButtonOption.getIconOptions();
		Button node = new ButtonTooltipDecorator(options.getToolTip(),
				new ButtonIconDecorator(options.getOnClick(),
						new SvgIconFactory(options))).makeNode(context);
		gp.add(node, row, 2);
	}

	@Override
	public void visit(NameFieldOption nameFieldOption, int row) {
		//noop
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		//noop
	}
}