package com.legyver.fenxlib.core.impl.config;

import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.config.options.TestApplicationOptionsBuilder;
import com.legyver.fenxlib.core.impl.config.options.init.RecentFilesApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.impl.files.FileRegistry;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.util.TestApplicationResource;
import com.legyver.fenxlib.core.impl.util.TestConfig;
import com.legyver.fenxlib.core.impl.util.TestUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.util.List;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;


public class FileBackedConfigTest extends ApplicationTest {

	@AfterEach
	public void reset() throws Exception {
		ApplicationContext.getFileRegistry().getOpenFiles().clear();
		ApplicationContext.getApplicationLifecycleHookRegistry().reset();
		ApplicationContext.setUiModel(null);
		ApplicationContext.setApplicationConfig(null);
	}

	/**
	 * @ignore Need a better way of testing this
	 * @throws Exception
	 */
	@Test
	public void openLastOpenedConfig() throws Exception {
		new TestApplicationOptionsBuilder()
				.appName("Test")
				.customAppConfigProvider(new TestApplicationResource("TestApplicationConfig_lastopened.json"))
				.customAppConfigInstantiator(map -> new TestConfig(map))
				.uiModel(new TestUiModel())
				.customRecentFilesMixin(new RecentFilesApplicationLifecycleHook() {
					@Override
					protected boolean isDirectoryValid(File file) {
						return true;
					}
				})
				.disableLogging()
				.disableAutosaveConfig()
				.build();//build() automatically bootstraps the application
		ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(LifecyclePhase.PRE_INIT);//RecentFilesMixin

		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		DefaultFileBrowseLocation defaultFileBrowseLocation = fileRegistry.getDefaultFileBrowseLocation();
		File browseDir = defaultFileBrowseLocation.getInitialDirectory();
		String expected = File.separator +  new StringJoiner(File.separator).add("temp").add("tmp").toString();
		assertThat(browseDir.getPath()).isEqualTo(expected);
	}

	/**
	 * @ignore Need a better way of testing this
	 * @throws Exception
	 */
	@Test
	public void openLastOpenedRecentlyModifiedConfig() throws Exception {
		new TestApplicationOptionsBuilder()
				.appName("Test")
				.customAppConfigProvider(new TestApplicationResource("TestApplicationConfig_lastAndRecentlyAdded.json"))
				.customAppConfigInstantiator(map -> new TestConfig(map))
				.uiModel(new TestUiModel())
				.customRecentFilesMixin(new RecentFilesApplicationLifecycleHook() {
					@Override
					protected boolean isFileValid(File file) {
						return true;
					}
				})
				.disableLogging()
				.disableAutosaveConfig()
				.build();//build() automatically bootstraps the application
		ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(LifecyclePhase.PRE_INIT);

		TestUiModel uiModel = (TestUiModel) ApplicationContext.getUiModel();
		List<FileOptions> fileOptions = uiModel.getRecentFiles();
		assertThat(fileOptions.size()).isEqualTo(2);
		//sorted by most recently accessed
		FileOptions file0 = fileOptions.get(0);
		assertThat(file0.getFileName()).isEqualTo("Name 2.ext");
		FileOptions file1 = fileOptions.get(1);
		assertThat(file1.getFileName()).isEqualTo("Name 1.ext");
	}

}
