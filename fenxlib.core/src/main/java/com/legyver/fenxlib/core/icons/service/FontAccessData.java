package com.legyver.fenxlib.core.icons.service;

import com.legyver.fenxlib.core.icons.fonts.FontMap;

import java.io.InputStream;

/**
 * Data necessary to load an icon font library
 */
public class FontAccessData {
	private final InputStream inputStream;
	private final String fontFamily;
	private final FontMap fontMap;
	private final int preference;

	/**
	 * Construct FontAccessData to load an icon font library
	 * @param inputStream the input stream for the icon ttf file
	 * @param fontFamily family of the font
	 * @param fontMap the font map for all icons to be displayed
	 * @param preference the preference for this font (higher number is lower preference)
	 */
	public FontAccessData(InputStream inputStream, String fontFamily, FontMap fontMap, int preference) {
		this.inputStream = inputStream;
		this.fontFamily = fontFamily;
		this.fontMap = fontMap;
		this.preference = preference;
	}

	/**'
	 * Construct FontAccessData to load an icon font library.  Uses the default preference of 0
	 * @param inputStream the input stream for the icon ttf file
	 * @param fontMap the font map for all icons to be displayed
	 * @param fontFamily family of the font
	 */
	public FontAccessData(InputStream inputStream, FontMap fontMap, String fontFamily) {
		this(inputStream, fontFamily, fontMap, 0);
	}

	/**
	 * Get the input stream for the icon font library definition file.
	 * We do it this way because of changes to the classloader permissions in Java 9+
	 * @return the input stream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * Get the font family.  This is used to allow for lazy installations of fonts
	 * @return the font family
	 */
	public String getFontFamily() {
		return fontFamily;
	}

	/**
	 * Get the font map that specifies the code to use for the icon name
	 * @return the font map
	 */
	public FontMap getFontMap() {
		return fontMap;
	}

	/**
	 * Get the preference for this Font resource.
	 * Higher number is lower preference.
	 * @return the preference
	 */
	public int getPreference() {
		return preference;
	}
}
