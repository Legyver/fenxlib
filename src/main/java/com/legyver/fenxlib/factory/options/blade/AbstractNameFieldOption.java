package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import com.legyver.fenxlib.factory.NodeInstantiator;
import com.legyver.fenxlib.factory.options.blade.instantiator.TextFieldInstantiator;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import javafx.beans.property.StringProperty;

public abstract class AbstractNameFieldOption extends AbstractBladeGridLayout implements TextFieldInstantiatorAware, LabeledBladeOption<StringProperty> {

	private final String label;
	private final boolean readOnly;
	private final TextFieldInstantiator instantiator;

	public AbstractNameFieldOption(String label, boolean readOnly, int labelSpan, final TextFieldInstantiator instantiator) {
		super(labelSpan, GRID_COLS - labelSpan);
		this.label = label;
		this.readOnly = readOnly;
		this.instantiator = instantiator;
	}

	public AbstractNameFieldOption(String label, boolean readOnly, final TextFieldInstantiator instantiator, ValidatorBase... validators) {
		this(label, readOnly, 1, instantiator);
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

	@Override
	public NodeInstantiator<JFXTextField> getInstantiator() {
		return instantiator;
	}

}
