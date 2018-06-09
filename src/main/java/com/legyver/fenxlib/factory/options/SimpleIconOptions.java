package com.legyver.fenxlib.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.visitor.IconOptionVisitor;
import javafx.scene.effect.BlendMode;

public class SimpleIconOptions extends IconOptions {

	public SimpleIconOptions(String icon, String iconColor, double iconSize, BlendMode blendMode) {
		super(icon, iconColor, iconSize, blendMode);
	}

	public SimpleIconOptions(String icon, String iconColor, double iconSize) {
		this(icon, iconColor, iconSize, null);
	}

	@Override
	public void accept(IconOptionVisitor visitor) throws CoreException {
		visitor.visit(this);
	}

}
