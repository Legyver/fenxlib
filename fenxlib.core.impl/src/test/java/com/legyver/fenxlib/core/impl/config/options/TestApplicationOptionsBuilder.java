package com.legyver.fenxlib.core.impl.config.options;

import com.legyver.fenxlib.core.config.options.init.RecentFilesApplicationLifecycleHook;

/**
 * Same package as ApplicationOptions because we want to use set to bypass file validation for tests
 * and that method is package-private
 */
public class TestApplicationOptionsBuilder extends ApplicationOptions.Builder<TestApplicationOptionsBuilder> {
	public TestApplicationOptionsBuilder customRecentFilesMixin(RecentFilesApplicationLifecycleHook recentFilesMixin) {
		return set(() -> this.recentFilesHook = recentFilesMixin);
	}
}
