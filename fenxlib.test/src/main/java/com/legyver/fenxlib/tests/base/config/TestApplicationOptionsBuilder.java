package com.legyver.fenxlib.tests.base.config;

import com.legyver.fenxlib.api.config.options.ApplicationOptions;
import com.legyver.fenxlib.api.lifecycle.hooks.RecentFilesApplicationLifecycleHook;
import com.legyver.fenxlib.tests.base.ui.TestUiModel;

import java.io.File;

/**
 * Same package as ApplicationOptions because we want to use set to bypass file validation for tests
 * and that method is package-private
 */
public class TestApplicationOptionsBuilder extends ApplicationOptions.Builder<TestApplicationOptionsBuilder> {

	/**
	 * add recent files support
	 * @param recentFilesMixin the mixin that adds recent file support
	 * @return this builder
	 */
	public TestApplicationOptionsBuilder customRecentFilesMixin(RecentFilesApplicationLifecycleHook recentFilesMixin) {
		return set(() -> this.recentFilesHook = recentFilesMixin);
	}

	/**
	 * Get the default test builder for further customization
	 * @return the default builder
	 */
	public static TestApplicationOptionsBuilder defaultBuilder() {
		return new TestApplicationOptionsBuilder()
				.appName("Test")
				.customAppConfigInstantiator(map -> new TestConfig())
				.uiModel(new TestUiModel())
				.customRecentFilesMixin(new RecentFilesApplicationLifecycleHook() {
					@Override
					protected boolean isDirectoryValid(File file) {
						return true;
					}
					@Override
					protected boolean isFileValid(File file) {
						return true;
					}
				})
				.disableLogging()
				.disableAutosaveConfig();
	}
}
