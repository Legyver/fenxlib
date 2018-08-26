package com.legyver.fenxlib.factory;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.core.exception.CoreException;
import javafx.scene.paint.Paint;
import com.legyver.fenxlib.factory.options.IconOptions;
import com.legyver.fenxlib.locator.LocationContext;

public class SvgIconFactory implements NodeFactory<SVGGlyph> {

	private final String fileName;
	private final IconOptions iconOptions;

	public SvgIconFactory(String fileName, IconOptions iconOptions) {
		this.fileName = fileName;
		this.iconOptions = iconOptions;
	}

	public SvgIconFactory(IconFont font, IconOptions iconOptions) {
		this(font.file, iconOptions);
	}

	public SvgIconFactory(IconOptions iconOptions) {
		this(IconFont.ICOMOON, iconOptions);
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

	/**
	 * Built-in fonts. Please note that FONT_AWESOME_BRAND fonts are copyrighted
	 * and should only be used when referring to that particular brand or
	 * service.
	 */
	public enum IconFont {
		ICOMOON("icomoon.svg"), FONT_AWESOME_REGULAR("fa-regular-400.svg"), FONT_AWESOME_BRAND("fa-brands-400.svg"), FONT_AWESOME_SOLID("fa-solid-900.svg");
		private final String file;
		private final String resourcePath = "/fonts/";

		IconFont(String file) {
			this.file = file;
		}

		public String getSVGFileName() {
			return file;
		}

		public String getResourcePath() {
			return resourcePath;
		}

	}
}
