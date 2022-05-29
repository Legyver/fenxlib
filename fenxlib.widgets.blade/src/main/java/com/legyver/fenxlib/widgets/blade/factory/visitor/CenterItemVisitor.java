package com.legyver.fenxlib.widgets.blade.factory.visitor;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.scene.controls.factory.ListViewFactory;
import com.legyver.fenxlib.core.scene.controls.factory.TextFieldFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.util.map.MapBuilder;
import com.legyver.fenxlib.widgets.blade.factory.BladeContext;
import com.legyver.fenxlib.widgets.blade.factory.blade.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Map;

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

	private Map textFieldArgs(boolean readOnly) {
		return new MapBuilder()
				.with(TextFieldFactory.IS_EDITABLE, !readOnly)
				.build();
	}

	private Map listViewArgs(boolean readOnly) {
		return new MapBuilder()
				.with(ListViewFactory.IS_EDITABLE, !readOnly)
				.build();
	}

	@Override
	public void visit(NameFieldButtonOption nameFieldButtonOption, int row) throws CoreException {
		locationContext.setName(nameFieldButtonOption.getLabel());
		TextField textField = ControlsFactory.make(TextField.class, locationContext, textFieldArgs(nameFieldButtonOption.isReadOnly()));
		bladeContext.getWorkingGrid().add(textField, nameFieldButtonOption.getLabelSpan(), row, nameFieldButtonOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(AbstractNameFieldOption nameFieldOption, int row) throws CoreException {
		locationContext.setName(nameFieldOption.getLabel());
		TextField textField = ControlsFactory.make(TextField.class, locationContext, textFieldArgs(nameFieldOption.isReadOnly()));
		bladeContext.getWorkingGrid().add(textField, nameFieldOption.getLabelSpan(), row, nameFieldOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameListClickOption nameListClickOption, int row) throws CoreException {
		locationContext.setName(nameListClickOption.getLabel());
		ListView listView = ControlsFactory.make(ListView.class, locationContext, listViewArgs(nameListClickOption.isReadOnly()));
		bladeContext.getWorkingGrid().add(listView, nameListClickOption.getLabelSpan(), row, nameListClickOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(NameDatePickerOption nameDatePickerOption, int row) throws CoreException {
		locationContext.setName(nameDatePickerOption.getLabel());
		DatePicker datePicker = ControlsFactory.make(DatePicker.class, locationContext);
		bladeContext.getWorkingGrid().add(datePicker, nameDatePickerOption.getLabelSpan(), row, nameDatePickerOption.getFieldSpan(), 1);
	}

	@Override
	public void visit(ShowMoreLabelOption showMoreOptions, int row) {
		//noop
	}
}
