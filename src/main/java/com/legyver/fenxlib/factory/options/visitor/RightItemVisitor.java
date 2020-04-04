package com.legyver.fenxlib.factory.options.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.EditSaveIconToggleFactory;
import com.legyver.fenxlib.factory.MoreFieldFactory;
import com.legyver.fenxlib.factory.NodeFactory;
import javafx.scene.control.Button;
import com.legyver.fenxlib.factory.SvgIconFactory;
import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.NameFieldButtonOption;
import com.legyver.fenxlib.factory.options.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.factory.options.blade.NameListClickOption;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.factory.decorator.ButtonIconDecorator;
import com.legyver.fenxlib.factory.decorator.ButtonTooltipDecorator;
import com.legyver.fenxlib.factory.decorator.CssClassDecorator;
import com.legyver.fenxlib.factory.options.blade.NameDatePickerOption;
import com.legyver.fenxlib.factory.options.TooltipIconOptions;
import com.legyver.fenxlib.factory.options.blade.NameSelectOption;
import com.legyver.fenxlib.factory.options.blade.ShowMoreLabelOption;
import com.legyver.fenxlib.factory.options.blade.UnlabeledEditableTextOption;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.widget.MoreField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

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

	@Override
	public void visit(UnlabeledEditableTextOption editableTextOption, int row) {
		try {
			TextField editableField = bladeContext.getEditableField();
			EditSaveIconToggleFactory factory = new EditSaveIconToggleFactory(editableTextOption.getEditIcon(), editableTextOption.getSaveIcon(), editableTextOption.getEditIconColor(), editableTextOption.getSaveIconColor());

			StackPane sp = factory.makeEditToggle(editableField, locationContext);
			bladeContext.getWorkingGrid().add(sp, 5, row);//edit/save the last item shown by default
		} catch (CoreException ex) {
			System.err.println(ex);//FIXME
		}
	}

	@Override
	public void visit(NameSelectOption option, int row) {
		//noop
	}

}
