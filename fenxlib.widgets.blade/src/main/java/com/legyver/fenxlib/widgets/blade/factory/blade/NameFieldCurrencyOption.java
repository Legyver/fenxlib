package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.core.controls.factory.NodeInstantiator;
import com.legyver.fenxlib.skins.number.CurrencyFieldSkin;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.scene.control.Skin;

/**
 * Layout option for
 *   &lt;Label&gt;&lt;CurrencyTextField&gt;
 */
public class NameFieldCurrencyOption extends AbstractNameFieldOption {

	/**
	 * Construct an option for a text field skinned to render with a currency sign to be included in the form
	 * @param label the label for the text field
	 * @param readOnly if the text field is read-only
	 * @param labelSpan the number of columns the label spans
	 */
	public NameFieldCurrencyOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan);
	}

	/**
	 * Construct an option for a text field skinned to render with a currency sign to be included in the form.
	 * The default label-span is 1.
	 * @param label the label for the text field
	 * @param readOnly if the text field is read-only
	 */
	public NameFieldCurrencyOption(String label, boolean readOnly) {
		super(label, readOnly);
	}

	@Override
	public NodeInstantiator<JFXTextField> getInstantiator() {
		return () -> new SkinnableNumberField("currency-field") {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new CurrencyFieldSkin(this);
			}
		};
	}


}
