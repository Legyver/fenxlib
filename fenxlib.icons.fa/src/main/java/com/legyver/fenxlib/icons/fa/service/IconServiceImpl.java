package com.legyver.fenxlib.icons.fa.service;

import com.legyver.fenxlib.core.api.icons.GlyphAccessData;
import com.legyver.fenxlib.core.api.icons.IconService;

import java.util.Arrays;
import java.util.List;

import static com.legyver.fenxlib.icons.fa.FontAwesomeIconFonts.FONTAWESOME_FREE_REGULAR;
import static com.legyver.fenxlib.icons.fa.FontAwesomeIconFonts.FONTAWESOME_FREE_SOLID;

/**
 * Load the fa icon libraries
 */
public class IconServiceImpl implements IconService {

	@Override
	public List<GlyphAccessData> iconsToLoad() {
		List<GlyphAccessData> glyphAccessData = Arrays.asList(
				new GlyphAccessData(IconServiceImpl.class.getResourceAsStream("fa-free-regular.svg"), FONTAWESOME_FREE_REGULAR),
				new GlyphAccessData(IconServiceImpl.class.getResourceAsStream("fa-free-solid.svg"), FONTAWESOME_FREE_SOLID)
		);
		return glyphAccessData;
	}
}
