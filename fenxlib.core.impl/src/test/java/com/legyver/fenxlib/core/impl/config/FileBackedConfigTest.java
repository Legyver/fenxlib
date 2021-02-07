package com.legyver.fenxlib.core.impl.config;

import com.legyver.fenxlib.core.impl.config.load.ApplicationHome;
import com.legyver.fenxlib.core.impl.config.options.ApplicationOptions;
import com.legyver.fenxlib.core.impl.config.options.init.RecentFilesApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.file.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.impl.files.FileIOUtil;
import com.legyver.fenxlib.core.impl.files.FileRegistry;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.uimodel.RecentFileAware;
import com.legyver.fenxlib.core.impl.util.TestConfig;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileBackedConfigTest extends ApplicationTest {

	@After
	public void reset() throws Exception {
		ApplicationContext.getFileRegistry().getOpenFiles().clear();
		ApplicationContext.getApplicationLifecycleHookRegistry().reset();
		ApplicationContext.setUiModel(null);
		ApplicationContext.setApplicationConfig(null);
	}

	@Test
	public void openLastOpenedConfig() throws Exception {
		new TestApplicationOptionsBuilder()
				.appName("Test")
				.customAppConfigProvider(new TestApplicationResource("TestApplicationConfig_lastopened.json"))
				.customAppConfigHandler(new TestConfigHandler())
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
		ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(LifecyclePhase.PRE_INIT);//RecentFilesMixin

		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		DefaultFileBrowseLocation defaultFileBrowseLocation = fileRegistry.getDefaultFileBrowseLocation();
		File browseDir = defaultFileBrowseLocation.getInitialDirectory();
		String expected = File.separator +  new StringJoiner(File.separator).add("temp").add("tmpDir").toString();
		assertThat(browseDir.getPath(), is(expected));
	}
	
	@Test
	public void openLastOpenedRecentlyModifiedConfig() throws Exception {
		new TestApplicationOptionsBuilder()
				.appName("Test")
				.customAppConfigProvider(new TestApplicationResource("TestApplicationConfig_lastAndRecentlyAdded.json"))
				.customAppConfigHandler(new TestConfigHandler())
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

		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		DefaultFileBrowseLocation defaultFileBrowseLocation = fileRegistry.getDefaultFileBrowseLocation();
		File browseDir = defaultFileBrowseLocation.getInitialDirectory();

		String expected = File.separator +  new StringJoiner(File.separator).add("temp").add("tmpDir").toString();
		assertThat(browseDir.getPath(), is(expected));

		TestUiModel uiModel = (TestUiModel) ApplicationContext.getUiModel();
		List<FileOptions> fileOptions = uiModel.getRecentFiles();
		assertThat(fileOptions.size(), is(2));
	}

	private class TestApplicationOptionsBuilder extends ApplicationOptions.Builder<TestApplicationOptionsBuilder> {
		public TestApplicationOptionsBuilder customRecentFilesMixin(RecentFilesApplicationLifecycleHook recentFilesMixin) {
			return set(() -> this.recentFilesHook = recentFilesMixin);
		}
	}

	private class TestConfigHandler extends ApplicationConfigHandler {
		//In test, ApplicationConfigHandler.instantiator is bypassed
		// FileIOUtil is just used for model mapping
		// (we read from src\test\resources rather than a file)
		public TestConfigHandler() {
			super(new FileIOUtil(), null);
		}
	}

	private class TestApplicationResource extends ApplicationHome {
		private final TestIOUtil testIOUtil = new TestIOUtil();

		public TestApplicationResource(String jsonTestFileToLoad) {
				super("Test");
			testIOUtil.jsonTestFileToLoad = jsonTestFileToLoad;
		}

		@Override
		public String getApplicationConfigAsString() throws IOException {
			return testIOUtil.loadFileToString(null);
		}
	}

	private class TestIOUtil extends FileIOUtil {
		private String jsonTestFileToLoad;

		@Override
		protected String loadFileToString(File file) throws IOException {
			return IOUtils.toString(getClass().getResourceAsStream(jsonTestFileToLoad), StandardCharsets.UTF_8);
		}
	}
	
	private class TestUiModel implements RecentFileAware {
		List<FileOptions> recentFiles = new ArrayList<>();

		@Override
		public List<FileOptions> getRecentFiles() {
			return recentFiles;
		}
		
	}
}
