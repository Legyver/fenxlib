package com.legyver.fenxlib.core.icons.service;

import java.util.List;

/**
 * Load Icon TTF Fonts
 * You are required to provide your own TTF
 */
public interface IconLoaderService {

	/**
	 * Gets list of icon files to load
	 * @return GlyphAccessData for files to load
	 */
	List<FontAccessData> iconsToLoad();

}
