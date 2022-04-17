package com.legyver.fenxlib.controls.icon;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.scene.control.SkinBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Skin for a SVGControl
 */
public class IconControlSkin extends SkinBase<IconControl> {
	private static final Logger logger = LogManager.getLogger(IconControlSkin.class);

	/**
	 * The icon to render
	 */
	private SVGGlyph glyph;

	/**
	 * Construct a skin for a SVGControl
	 * @param iconControl the control to skin
	 */
	public IconControlSkin(IconControl iconControl) {
		super(iconControl);
		String name = iconControl.getIconFontFamily() + "." + iconControl.getIconName();
		try {
			glyph = SVGGlyphLoader.getIcoMoonGlyph(name);
			glyph.setFill(iconControl.getIconPaint());
			glyph.setSize(iconControl.getIconSize());
		} catch (Exception exception) {
			logger.error("Error loading control: " + name, exception);
		}

		getChildren().add(glyph);
	}
}
