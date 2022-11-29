package it.config.json;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibTestConfiguration;
import com.legyver.fenxlib.tests.base.ui.TestUiModel;
import org.junit.jupiter.api.Test;
import org.testfx.assertions.api.Assertions;

import java.util.List;

@FenxlibTestConfiguration(configFile = "TestApplicationConfig_lastAndRecentlyAdded.json")
public class LastOpenedRecentFilesConfigTest extends BaseJsonConfigFenxlibTest {

	@Test
	public void openLastOpenedRecentlyModifiedConfig() {
		TestUiModel uiModel = (TestUiModel) ApplicationContext.getUiModel();
		List<FileOptions> fileOptions = uiModel.getRecentFiles();
		Assertions.assertThat(fileOptions.size()).isEqualTo(2);
		//sorted by most recently accessed
		FileOptions file0 = fileOptions.get(0);
		Assertions.assertThat(file0.getFileName()).isEqualTo("Name 2.ext");
		FileOptions file1 = fileOptions.get(1);
		Assertions.assertThat(file1.getFileName()).isEqualTo("Name 1.ext");
	}

}
