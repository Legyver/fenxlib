package com.legyver.fenxlib.core.impl.factory;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.paint.Paint;
import com.legyver.fenxlib.core.impl.factory.options.IconOptions;

/**
 * Factory to create a SVGGlyph
 */
public class SvgIconFactory implements NodeFactory<SVGGlyph> {
	private final String fileName =  "icomoon.svg";
	private final IconOptions iconOptions;

	public SvgIconFactory(IconOptions iconOptions) {
		this.iconOptions = iconOptions;
	}

	@Override
	public SVGGlyph makeNode(LocationContext locationContext) throws CoreException {
		SVGGlyph glyph = null;
		try {
			glyph = SVGGlyphLoader.getIcoMoonGlyph(fileName + "." + iconOptions.getIcon());
			glyph.setSize(iconOptions.getIconSize());
			glyph.setFill(Paint.valueOf(iconOptions.getIconColor()));
			if (iconOptions.getBlendMode() != null) {
				glyph.setBlendMode(iconOptions.getBlendMode());
			}
		} catch (Exception e) {
			throw new CoreException(e);
		}
		return glyph;
	}
}
