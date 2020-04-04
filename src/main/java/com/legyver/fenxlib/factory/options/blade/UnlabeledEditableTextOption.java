package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.NodeInstantiator;
import com.legyver.fenxlib.factory.options.blade.instantiator.TextFieldInstantiator;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;

public class UnlabeledEditableTextOption implements BladeOption, TextFieldInstantiatorAware {

	private final String bindLabel;//since the're no label, we need to bind as something unique
	private final String editIcon;
	private final String editIconColor;
	private final String saveIcon;
	private final String saveIconColor;
	private final TextFieldInstantiator instantiator;

	public UnlabeledEditableTextOption(String bindLabel, String editIcon, String editIconColor, String saveIcon, String saveIconColor, TextFieldInstantiator instantiator) {
		this.bindLabel = bindLabel;
		this.editIcon = editIcon;
		this.editIconColor = editIconColor;
		this.saveIcon = saveIcon;
		this.saveIconColor = saveIconColor;
		this.instantiator = instantiator;
	}

	public UnlabeledEditableTextOption(String bindLabel, TextFieldInstantiator instantiator) {
		this(bindLabel, "fa-pencil", "#334db3", "fa-save", "#334db3", instantiator);
	}

	public UnlabeledEditableTextOption(String bindLabel) {
		this(bindLabel, TextFieldInstantiator.PLAIN_TEXT);
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	public String getBindLabel() {
		return bindLabel;
	}

	public String getEditIcon() {
		return editIcon;
	}

	public String getEditIconColor() {
		return editIconColor;
	}

	public String getSaveIcon() {
		return saveIcon;
	}

	public String getSaveIconColor() {
		return saveIconColor;
	}

	@Override
	public NodeInstantiator<JFXTextField> getInstantiator() {
		return instantiator;
	}

}
