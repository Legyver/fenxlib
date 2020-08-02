package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.MoreFieldFactory;
import com.legyver.fenxlib.core.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.SvgIconFactory;
import com.legyver.fenxlib.core.factory.decorator.ButtonIconDecorator;
import com.legyver.fenxlib.core.factory.decorator.ButtonTooltipDecorator;
import com.legyver.fenxlib.core.factory.decorator.CssClassDecorator;
import com.legyver.fenxlib.widgets.blade.factory.options.TooltipIconOptions;

import com.legyver.fenxlib.core.locator.LocationContext;

import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;

import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.widgets.blade.MoreField;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class RightItemVisitor extends AbstractGridPaneLayoutVisitor {

	public RightItemVisitor(GridPane gp, LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException {
		TooltipIconOptions options = nameFieldButtonOption.getIconOptions();
		Button node = new ButtonTooltipDecorator(options.getToolTip(),
				new ButtonIconDecorator(options.getOnClick(),
						new SvgIconFactory(options))).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(node, 4, row);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		//noop
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		//noop
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		//noop
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) throws CoreException {
		NodeFactory<MoreField> moreFieldFactory = new CssClassDecorator<>(showMoreOptions.getCssClass(),
				new MoreFieldFactory(showMoreOptions.getShowText(), showMoreOptions.getHideText()));
		MoreField label = moreFieldFactory.makeNode(locationContext);
		bladeContext.getWorkingGrid().add(label, 4, row, 2, 1);//more label the last item shown by default		
		bladeContext.setMoreTrigger(label);
	}
}
