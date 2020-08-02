package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.core.factory.NodeInstantiator;
import com.legyver.fenxlib.skins.number.CurrencyFieldSkin;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
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
