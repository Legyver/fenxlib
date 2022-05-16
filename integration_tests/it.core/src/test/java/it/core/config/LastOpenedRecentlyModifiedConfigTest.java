package it.core.config;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibConfiguration;
import com.legyver.fenxlib.tests.base.ui.TestUiModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FenxlibConfiguration("TestApplicationConfig_lastAndRecentlyAdded.json")
public class LastOpenedRecentlyModifiedConfigTest extends FenxlibTest {

	/**
	 * @ignore Need a better way of testing this
	 */
	@Test
	public void openLastOpenedRecentlyModifiedConfig() {
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
