package com.legyver.fenxlib.core.api.icons;

import java.io.InputStream;

/**
 * Data necessary to load an icon font library
 */
public class GlyphAccessData {
	private final InputStream inputStream;
	private final String prefix;

	/**
	 * Construct GlyphAccess data to load an icon font library
	 * @param inputStream the input stream for the icon svg file
	 * @param prefix the prefix to associate with icons of the library
	 */
	public GlyphAccessData(InputStream inputStream, String prefix) {
		this.inputStream = inputStream;
		this.prefix = prefix;
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
	 * The prefix to associate with icons in this icon font library
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
}
