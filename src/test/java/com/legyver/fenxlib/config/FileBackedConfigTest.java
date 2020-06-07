package com.legyver.fenxlib.config;

import com.legyver.fenxlib.config.load.AppHome;
import com.legyver.fenxlib.config.options.FileBasedApplicationOptions;
import com.legyver.fenxlib.files.FileIOUtil;
import com.legyver.fenxlib.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.uimodel.FileOptions;
import com.legyver.fenxlib.uimodel.RecentFileAware;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.io.IOUtils;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class FileBackedConfigTest extends ApplicationTest {

	@Test
	public void openLastOpenedConfig() throws Exception {
		TestFileBasedApplicationOptions options = new TestFileBasedApplicationOptions("TestApplicationConfig_lastopened.json");
		File lastOpened = options.getLastModifiedParentDir();
		String expected = File.separator +  new StringJoiner(File.separator).add("temp").add("tmpDir").toString();
		assertThat(lastOpened.getPath(), is(expected));
	}
	
	@Test
	public void openLastOpenedRecentlyModifiedConfig() throws Exception {
		TestFileBasedApplicationOptions options = new TestFileBasedApplicationOptions("TestApplicationConfig_lastAndRecentlyAdded.json");
		File lastOpened = options.getLastModifiedParentDir();
		String expected = File.separator +  new StringJoiner(File.separator).add("temp").add("tmpDir").toString();
		assertThat(lastOpened.getPath(), is(expected));
		TestUiModel uiModel = options.getUiModel();
		List<FileOptions> fileOptions = uiModel.getRecentFiles();
		assertThat(fileOptions.size(), is(2));
	}
	
	private class TestFileBasedApplicationOptions extends FileBasedApplicationOptions<TestUiModel> {
		
		public TestFileBasedApplicationOptions(String jsonTestFileToLoad) throws IOException, IllegalAccessException {
			super(new TestAppResource(jsonTestFileToLoad), null, new TestUiModel(), new TestConfigHandler());
		}
		
		@Override
		protected void firePreInit() {
			setFileValidateExistence(false);
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

	private class TestAppResource extends AppHome {
		private final TestIOUtil testIOUtil = new TestIOUtil();

		public TestAppResource(String jsonTestFileToLoad) {
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
		FileOptions workingFile = new DefaultFileOptions();
		List<FileOptions> recentFiles = new ArrayList<>();
		
		@Override
		public FileOptions getWorkingFileOptions() {
			return workingFile;
		}

		@Override
		public List<FileOptions> getRecentFiles() {
			return recentFiles;
		}
		
	}
}
