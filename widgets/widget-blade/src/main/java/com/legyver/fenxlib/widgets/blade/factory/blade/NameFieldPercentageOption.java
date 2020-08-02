package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.core.factory.NodeInstantiator;
import com.legyver.fenxlib.skins.number.PercentageFieldSkin;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.scene.control.Skin;

public class NameFieldPercentageOption extends AbstractNameFieldOption {

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
