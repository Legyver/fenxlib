package com.legyver.fenxlib.core.impl.icons;

import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.icons.GlyphAccessData;
import com.legyver.fenxlib.core.api.icons.IconService;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionVoidActionDecorator;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class IconServiceRegistry {
	private final ServiceLoader<IconService> iconServiceLoader;
	private static IconServiceRegistry instance;

	public IconServiceRegistry() {
		this.iconServiceLoader = ServiceLoader.load(IconService.class);
	}

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
