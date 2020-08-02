package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.options.IconOptions;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;
import javafx.scene.effect.BlendMode;

public class SimpleIconOptions extends IconOptions implements IconWidgetVisitorOptions {

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
