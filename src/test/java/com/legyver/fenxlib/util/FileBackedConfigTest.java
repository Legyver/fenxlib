package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.ApplicationConfigHandler;
import com.legyver.fenxlib.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.uimodel.FileOptions;
import com.legyver.fenxlib.uimodel.RecentFileAware;
import java.io.File;
import java.io.IOException;
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
			super(null, new TestUiModel(), new ApplicationConfigHandler(".test", new TestIOUtil(jsonTestFileToLoad), (map) -> new TestConfig(map)));
		}
		
		@Override
		protected void firePreInit() {
			setFileValidateExistence(false);
		}
		
	}
	
	private class TestIOUtil extends FileIOUtil {
		private final String jsonTestFileToLoad;

		public TestIOUtil(String jsonTestFileToLoad) {
			this.jsonTestFileToLoad = jsonTestFileToLoad;
		}
		
		@Override
		protected String loadFileToString(File file) throws IOException {
			return IOUtils.toString(getClass().getResourceAsStream(jsonTestFileToLoad), "UTF-8");
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
