package com.legyver.fenxlib.core.impl.config.options.init;

import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.factory.SvgIconFactory;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionConsumerDecorator;

import java.io.InputStream;

public class SVGGlyphLoadingApplicationLifecycleHook implements ApplicationLifecycleHook {
	private final String glyphKeyPrefix;
	private final String resourceName;

	public SVGGlyphLoadingApplicationLifecycleHook(String glyphKeyPrefix, String resourceName) {
		this.glyphKeyPrefix = glyphKeyPrefix;
		this.resourceName = resourceName;
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> new ExceptionToCoreExceptionConsumerDecorator<>((InputStream is) -> SVGGlyphLoader.loadGlyphsFont(is, glyphKeyPrefix))
				.accept(SvgIconFactory.class.getResourceAsStream(resourceName));
	}
}
