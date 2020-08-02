package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import com.legyver.fenxlib.core.factory.NodeInstantiator;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.property.StringProperty;

public abstract class AbstractNameFieldOption extends AbstractBladeGridLayout implements BladeOption<StringProperty> {

	private final String label;
	private final boolean readOnly;

	public AbstractNameFieldOption(String label, boolean readOnly, int labelSpan) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
		this.readOnly = readOnly;
	}

	public AbstractNameFieldOption(String label, boolean readOnly, ValidatorBase... validators) {
		this(label, readOnly, 1);
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) {
		visitor.visit(this, row);
	}

	public String getLabel() {
		return label;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public abstract NodeInstantiator<JFXTextField> getInstantiator();

}
