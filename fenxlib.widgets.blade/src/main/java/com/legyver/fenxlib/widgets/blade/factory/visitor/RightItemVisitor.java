package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.decorator.ButtonIconDecorator;
import com.legyver.fenxlib.core.controls.decorator.ButtonTooltipDecorator;
import com.legyver.fenxlib.core.controls.decorator.CssClassDecorator;
import com.legyver.fenxlib.core.controls.factory.IconFactory;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.widgets.blade.factory.MoreFieldFactory;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.widgets.blade.factory.options.TooltipIconOptions;
import com.legyver.fenxlib.widgets.blade.MoreField;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;

import javafx.scene.control.Button;

/**
 * Visitor to apply the right item to a grid-layout
 */
public class RightItemVisitor extends AbstractGridPaneLayoutVisitor {

	/**
	 * Construct a RightItemVisitor given the location context and blade context
	 * @param handleContext the location context to apply to the right item
	 * @param bladeContext the blade context to apply to the right item
	 */
	public RightItemVisitor(LocationContext handleContext, BladeContext bladeContext) {
		super(handleContext, bladeContext);
	}

	/**
	 * Add a button to the grid.
	 * @param nameFieldButtonOption holds the options to apply to the button
	 * @param row the current row
	 * @throws CoreException if any of the nested factories throw a CoreException
	 */
	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException {
		TooltipIconOptions options = nameFieldButtonOption.getIconOptions();
		Button node = new ButtonTooltipDecorator(options.getToolTip(),
				(NodeFactory<Button>) new ButtonIconDecorator(options.getOnClick(),
						new IconFactory(options))).makeNode(locationContext);
		bladeContext.getWorkingGrid().add(node, 4, row);
	}

	/**
	 * Noop
	 * @param nameFieldOption ignored
	 * @param row ignored
	 */
	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) {
		//noop
	}

	/**
	 * Noop
	 * @param nameListClickOption ignored
	 * @param row ignored
	 */
	@Override
	public void visit(NameListClickOption nameListClickOption, int row) {
		//noop
	}

	/**
	 * Noop
	 * @param nameDatePickerOption ignored
	 * @param row ignored
	 */
	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) {
		//noop
	}

	/**
	 * Apply a "show/hide more" to the right item that when clicked shows hidden rows
	 * @param showMoreOptions contains the css/text to be displayed
	 * @param row the row to apply this to
	 * @throws CoreException if any of the nested factories throw a CoreException
	 */
	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) throws CoreException {
		StyleableFactory<MoreField> moreFieldFactory = new CssClassDecorator<>(showMoreOptions.getCssClass(),
				new MoreFieldFactory(showMoreOptions.getShowText(), showMoreOptions.getHideText()));
		MoreField label = moreFieldFactory.makeNode(locationContext);
		bladeContext.getWorkingGrid().add(label, 4, row, 2, 1);//more label the last item shown by default		
		bladeContext.setMoreTrigger(label);
	}
}
