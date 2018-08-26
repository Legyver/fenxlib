package com.legyver.fenxlib.factory;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.fenxlib.factory.SvgIconFactory.IconFont;
import com.legyver.fenxlib.factory.options.IconOptions;
import com.legyver.fenxlib.factory.options.SimpleIconOptions;
import com.legyver.fenxlib.locator.DefaultLocationContext;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SvgIconFactoryTest {

	@Test
	public void icomoon() throws Exception {
		testFont(IconFont.ICOMOON, new SimpleIconOptions("trash", "#999999", 15));
	}

	@Test
	public void fontAwesomeRegular() throws Exception {
		testFont(IconFont.FONT_AWESOME_REGULAR, new SimpleIconOptions("window-minimize", "#999999", 15));
	}

	@Test
	public void fontAwesomeSolid() throws Exception {
		testFont(IconFont.FONT_AWESOME_SOLID, new SimpleIconOptions("window-minimize", "#999999", 15));
	}

	@Test
	public void fontAwesomeBrand() throws Exception {
		testFont(IconFont.FONT_AWESOME_BRAND, new SimpleIconOptions("java", "#999999", 15));
	}

	private void testFont(IconFont iconFont, IconOptions options) throws Exception {
		loadIconFontFile(iconFont);
		SvgIconFactory factory = new SvgIconFactory(iconFont, options);
		SVGGlyph glyph = factory.makeNode(new DefaultLocationContext("test"));
		assertNotNull("no Glyph found", glyph);
	}

	private void loadIconFontFile(IconFont iconFont) throws IOException {
		SVGGlyphLoader.loadGlyphsFont(SvgIconFactory.class.getResourceAsStream(iconFont.getResourcePath() + iconFont.getSVGFileName()), iconFont.getSVGFileName());
	}
}
