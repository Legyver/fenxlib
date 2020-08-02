package com.legyver.fenxlib.core.config.options.mixins;

import com.jfoenix.svg.SVGGlyphLoader;
import com.legyver.fenxlib.core.factory.SvgIconFactory;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;
import com.legyver.util.adaptex.ExceptionToCoreExceptionConsumerDecorator;

import java.io.InputStream;

public class SVGGlyphLoadingMixin implements HookRegistrationMixin {
	private final String glyphKeyPrefix;
	private final String resourceName;

	public SVGGlyphLoadingMixin(String glyphKeyPrefix, String resourceName) {
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
