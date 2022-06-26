package it.config.json;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibConfiguration;
import com.legyver.fenxlib.tests.base.ui.TestUiModel;
import org.junit.jupiter.api.Test;
import org.testfx.assertions.api.Assertions;

import java.util.List;

@FenxlibConfiguration("TestApplicationConfig_lastAndRecentlyAdded.json")
public class LastOpenedRecentlyModifiedConfigTest extends BaseJsonConfigFenxlibTest {

	/**
	 * @ignore Need a better way of testing this
	 */
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