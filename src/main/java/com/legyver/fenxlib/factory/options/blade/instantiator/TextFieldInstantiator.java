package com.legyver.fenxlib.factory.options.blade.instantiator;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.factory.NodeInstantiator;
import com.legyver.fenxlib.skin.PercentageFieldSkin;
import com.legyver.fenxlib.skin.SkinnableNumberField;
import javafx.scene.control.Skin;
import com.legyver.fenxlib.skin.CurrencyFieldSkin;

public enum TextFieldInstantiator implements NodeInstantiator<JFXTextField> {
	PLAIN_TEXT {
		@Override
		public JFXTextField newInstance() {
			return new JFXTextField();
		}
	}, PERCENT {

		@Override
		public JFXTextField newInstance() {
			return new SkinnableNumberField("percentage-field") {
				@Override
				protected Skin<?> createDefaultSkin() {
					return new PercentageFieldSkin(this);
				}
			};
		}
	}, CURRENCY {
		@Override
		public JFXTextField newInstance() {
			return new SkinnableNumberField("currency-field") {
				@Override
				protected Skin<?> createDefaultSkin() {
					return new CurrencyFieldSkin(this);
				}
			};
		}
	};
}
