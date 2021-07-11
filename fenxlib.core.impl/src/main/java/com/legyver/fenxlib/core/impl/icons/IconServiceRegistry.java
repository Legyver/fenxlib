package com.legyver.fenxlib.core.impl.icons;

import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.icons.GlyphAccessData;
import com.legyver.fenxlib.core.api.icons.IconService;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionVoidActionDecorator;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Registry for Icon Services
 * This allows for any IconService in the classpath to be picked up (along with license information)
 */
public class IconServiceRegistry {
	private final ServiceLoader<IconService> iconServiceLoader;
	private static IconServiceRegistry instance;

	/**
	 * Construct an registry for Icon Services
	 */
	public IconServiceRegistry() {
		this.iconServiceLoader = ServiceLoader.load(IconService.class);
	}

	/**
	 * Get the singleton instance of the service registry for IconServices
	 * @return the singleton instance
	 */
	public static IconServiceRegistry getInstance() {
		if (instance == null) {
			synchronized (IconServiceRegistry.class) {
				if (instance == null) {
					instance = new IconServiceRegistry();
				}
			}
		}
		return instance;
	}

	/**
	 * Load all icons from all IconServices that are on the classpath
	 * @throws CoreException if there is an error loading any of the icons
	 */
	public void loadIcons() throws CoreException {
		for (Iterator<IconService> it = iconServiceLoader.iterator(); it.hasNext(); ) {
			IconService service = it.next();
			List<GlyphAccessData> glyphAccessDataList = service.iconsToLoad();
			for (GlyphAccessData glyphAccessData : glyphAccessDataList) {
				new ExceptionToCoreExceptionVoidActionDecorator(() -> {
						SVGGlyphLoader.loadGlyphsFont(glyphAccessData.getInputStream(), glyphAccessData.getPrefix());
				}).execute();
			}
		}
	}
}
