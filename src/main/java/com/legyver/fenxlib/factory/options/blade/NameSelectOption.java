package com.legyver.fenxlib.factory.options.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.StringOptionsGroup;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import java.util.List;

/**
 * name: [Drop-down]
 */
public class NameSelectOption extends AbstractBladeGridLayout implements LabeledBladeOption {

	private StringOptionsGroup stringOptionsGroup;
	private String label;

	public NameSelectOption(String label, StringOptionsGroup stringOptionsGroup, int labelSpan) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
		this.stringOptionsGroup = stringOptionsGroup;
	}

	public NameSelectOption(String label, StringOptionsGroup stringOptionsGroup) {
		this(label, stringOptionsGroup, 1);
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	@Override
	public String getLabel() {
		return label;
	}

	public List<String> getItems() {
		return stringOptionsGroup.getOptions();
	}

}
