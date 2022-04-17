package com.legyver.fenxlib.core.icons.service.internal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.icons.IconRegistry;
import com.legyver.fenxlib.core.icons.service.FontAccessData;
import com.legyver.fenxlib.core.icons.service.IconLoaderService;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionVoidActionDecorator;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Registry for Icon Services
 * This allows for any IconService in the classpath to be picked up (along with license information)
 */
public class IconServiceRegistry {
	private final ServiceLoader<IconLoaderService> iconServiceLoader;
	private static IconServiceRegistry instance;

	/**
	 * Construct an registry for Icon Services
	 */
	public IconServiceRegistry() {
		this.iconServiceLoader = ServiceLoader.load(IconLoaderService.class);
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
		for (Iterator<IconLoaderService> it = iconServiceLoader.iterator(); it.hasNext(); ) {
			IconLoaderService service = it.next();
			List<FontAccessData> fontAccessDataList = service.iconsToLoad();
			for (FontAccessData fontAccessData : fontAccessDataList) {
				new ExceptionToCoreExceptionVoidActionDecorator(() -> {
						IconRegistry.getInstance().load(fontAccessData.getInputStream(), fontAccessData.getFontFamily(), fontAccessData.getFontMap(), fontAccessData.getPreference());
				}).execute();
			}
		}
	}
}
