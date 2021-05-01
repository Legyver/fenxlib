package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.icons.IconServiceRegistry;

/**
 * Lifecycle hook to load icons when the application loads
 */
public class SVGGlyphLoadingApplicationLifecycleHook implements ApplicationLifecycleHook {

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> IconServiceRegistry.getInstance().loadIcons();
	}
}
