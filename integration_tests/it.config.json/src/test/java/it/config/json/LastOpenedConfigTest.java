package it.config.json;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileBrowseLocation;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibConfiguration;
import org.junit.jupiter.api.Test;
import org.testfx.assertions.api.Assertions;

import java.io.File;
import java.util.StringJoiner;

@FenxlibConfiguration(configFile = "TestApplicationConfig_lastopened.json")
public class LastOpenedConfigTest extends BaseJsonConfigFenxlibTest {

	@Test
	public void openLastOpenedConfig() throws Exception {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		DefaultFileBrowseLocation defaultFileBrowseLocation = fileRegistry.getDefaultFileBrowseLocation();
		File browseDir = defaultFileBrowseLocation.getInitialDirectory();
		String expected = File.separator + new StringJoiner(File.separator).add("temp").add("tmp");
		Assertions.assertThat(browseDir.getPath()).isEqualTo(expected);
	}

}
