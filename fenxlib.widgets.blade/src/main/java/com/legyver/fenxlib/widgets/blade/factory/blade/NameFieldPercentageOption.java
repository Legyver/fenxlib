package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.core.controls.factory.NodeInstantiator;
import com.legyver.fenxlib.skins.number.PercentageFieldSkin;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.scene.control.Skin;

/**
 * Layout option for
 *   &lt;Label&gt;&lt;PercentageField&gt;
 */
public class NameFieldPercentageOption extends AbstractNameFieldOption {

	/**
	 * Construct an option for a text field skinned to render with a percent sign to be included in the form
	 * @param label the label for the text field
	 * @param readOnly if the text field is read-only
	 * @param labelSpan how many columns the label spans
	 */
	public NameFieldPercentageOption(String label, boolean readOnly,int labelSpan) {
		super(label, readOnly, labelSpan);
	}

	@Override
	public NodeInstantiator<JFXTextField> getInstantiator() {
		return () -> new SkinnableNumberField("percentage-field") {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new PercentageFieldSkin(this);
			}
		};
	}

}
