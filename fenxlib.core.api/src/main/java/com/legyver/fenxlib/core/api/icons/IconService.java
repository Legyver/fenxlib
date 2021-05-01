package com.legyver.fenxlib.core.api.icons;

import java.util.List;

/**
 * Load SVG Icon Fonts
 * There is a default implementation that loads iconmoon, and two free font-awesome font libraries.
 * You may also implement your own icon service for additional fonts
 */
public interface IconService {

	/**
	 * Gets list of icon files to load
	 * @return GlyphAccessData for files to load
	 */
	List<GlyphAccessData> iconsToLoad();

}
