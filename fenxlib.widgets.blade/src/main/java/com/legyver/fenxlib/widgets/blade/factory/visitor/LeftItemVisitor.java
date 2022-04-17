package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.controls.factory.LabelFactory;
import com.legyver.fenxlib.core.util.MapBuilder;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameDatePickerOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameFieldButtonOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.AbstractNameFieldOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import com.legyver.fenxlib.widgets.blade.factory.blade.ShowMoreLabelOption;
import javafx.scene.control.Label;
import com.legyver.fenxlib.core.locator.LocationContext;

import java.util.Map;

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

	private Map labelArgs(String text) {
		return new MapBuilder()
				.with(LabelFactory.DEFAULT_TEXT, text)
				.build();
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException {
		Label label = ControlsFactory.make(Label.class, labelArgs(nameFieldButtonOption.getLabel()));
		bladeContext.getWorkingGrid().add(label, 0, row, nameFieldButtonOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) throws CoreException {
		Label label = ControlsFactory.make(Label.class, labelArgs(nameFieldOption.getLabel()));
		bladeContext.getWorkingGrid().add(label, 0, row, nameFieldOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) throws CoreException {
		Label label = ControlsFactory.make(Label.class, labelArgs(nameListClickOption.getLabel()));
		bladeContext.getWorkingGrid().add(label, 0, row, nameListClickOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) throws CoreException {
		Label label = ControlsFactory.make(Label.class, labelArgs(nameDatePickerOption.getLabel()));
		bladeContext.getWorkingGrid().add(label, 0, row, nameDatePickerOption.getLabelSpan(), 1);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		//noop
	}
}
