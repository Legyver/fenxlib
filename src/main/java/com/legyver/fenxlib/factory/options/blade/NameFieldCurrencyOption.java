package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.factory.NodeInstantiator;
import com.legyver.fenxlib.skin.CurrencyFieldSkin;
import com.legyver.fenxlib.skin.SkinnableNumberField;
import javafx.scene.control.Skin;

public class NameFieldCurrencyOption extends AbstractNameFieldOption {

	public NameFieldCurrencyOption(String label, boolean readOnly, int labelSpan) {
		super(label, readOnly, labelSpan);
	}

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
