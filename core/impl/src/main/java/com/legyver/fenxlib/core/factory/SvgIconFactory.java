package com.legyver.fenxlib.core.factory;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.core.exception.CoreException;
import javafx.scene.paint.Paint;
import com.legyver.fenxlib.core.factory.options.IconOptions;
import com.legyver.fenxlib.core.locator.LocationContext;

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
