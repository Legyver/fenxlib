package com.legyver.fenxlib.controls.icon;

import com.legyver.fenxlib.core.icons.IconRegistry;
import com.legyver.fenxlib.api.icons.options.IconOptions;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
	private Text glyph;

	/**
	 * Construct a skin for a SVGControl
	 * @param iconControl the control to skin
	 */
	public IconControlSkin(IconControl iconControl) {
		super(iconControl);
		String name = iconControl.getIconFontFamily() + "." + iconControl.getIconName();
		try {
			IconOptions iconOptions = new IconOptions.Builder()
					.family(iconControl.getIconFontFamily())
					.icon(iconControl.getIconName())
					.iconSize(iconControl.getIconSize())
					.iconColor((Color) iconControl.getIconPaint())
					.build();
			glyph = IconRegistry.getInstance().getIcon(iconOptions);
		} catch (Exception exception) {
			logger.error("Error loading control: " + name, exception);
		}

		getChildren().add(glyph);
	}
}
