package com.legyver.fenxlib.icons.icomoon.service;

import com.legyver.fenxlib.core.api.icons.GlyphAccessData;
import com.legyver.fenxlib.core.api.icons.IconService;

import java.util.Arrays;
import java.util.List;

import static com.legyver.fenxlib.icons.icomoon.IcoMoonFonts.ICO_MOON;

/**
 * Load the IcoMoon icon library
 */
public class IconServiceImpl implements IconService {

	@Override
	public List<GlyphAccessData> iconsToLoad() {
		List<GlyphAccessData> glyphAccessData = Arrays.asList(
				new GlyphAccessData(IconServiceImpl.class.getResourceAsStream("icomoon.svg"), ICO_MOON)
		);
		return glyphAccessData;
	}
}
