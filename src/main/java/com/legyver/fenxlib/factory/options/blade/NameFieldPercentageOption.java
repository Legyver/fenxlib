package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.factory.NodeInstantiator;
import com.legyver.fenxlib.skin.PercentageFieldSkin;
import com.legyver.fenxlib.skin.SkinnableNumberField;
import javafx.scene.control.Skin;

public class NameFieldPercentageOption extends AbstractNameFieldOption {

	public NameFieldPercentageOption(String label, boolean readOnly, int labelSpan) {
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
