package com.legyver.fenxlib.controls.svg;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.scene.control.SkinBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SVGControlSkin extends SkinBase<SVGControl> {
	private static final Logger logger = LogManager.getLogger(SVGControlSkin.class);

	private SVGGlyph glyph;

	public SVGControlSkin(SVGControl svgControl) {
		super(svgControl);
		String name = svgControl.getSvgIconLibraryPrefix() + "." + svgControl.getSvgIcon();
		try {
			glyph = SVGGlyphLoader.getIcoMoonGlyph(name);
			glyph.setFill(svgControl.getSvgIconPaint());
			glyph.setSize(svgControl.getSvgIconSize());
		} catch (Exception exception) {
			logger.error("Error loading control: " + name, exception);
		}

		getChildren().add(glyph);
	}
}
