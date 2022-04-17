package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;
import javafx.scene.effect.BlendMode;

/**
 * Options describing an icon
 */
public class SimpleIconOptions extends IconOptions implements IconWidgetVisitorOptions {

	/**
	 * Specify the icon, color, size and blend mode to use
	 * @param icon the icon
	 * @param iconFamily the family of the icon font
	 * @param iconColor the color
	 * @param iconSize the size
	 * @param blendMode the blend mode
	 */
	public SimpleIconOptions(String icon, String iconFamily, String iconColor, double iconSize, BlendMode blendMode) {
		super(icon, iconFamily, iconColor, iconSize, blendMode);
	}

	/**
	 * Specify the icon, color and size
	 * @param icon the icon
	 * @param iconFamily the family of the icon font
	 * @param iconColor the color of the icon
	 * @param iconSize the size of the icon
	 */
	public SimpleIconOptions(String icon, String iconFamily, String iconColor, double iconSize) {
		this(icon, iconFamily, iconColor, iconSize, null);
	}

	@Override
	public void accept(IconOptionVisitor visitor) throws CoreException {
		visitor.visit(this);
	}

}
