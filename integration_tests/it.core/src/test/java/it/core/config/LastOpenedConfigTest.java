package it.core.config;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileBrowseLocation;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibConfiguration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;

@FenxlibConfiguration("TestApplicationConfig_lastopened.json")
public class LastOpenedConfigTest extends FenxlibTest {

	/**
	 * @throws Exception
	 */
	@Test
	public void openLastOpenedConfig() throws Exception {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		DefaultFileBrowseLocation defaultFileBrowseLocation = fileRegistry.getDefaultFileBrowseLocation();
		File browseDir = defaultFileBrowseLocation.getInitialDirectory();
		String expected = File.separator + new StringJoiner(File.separator).add("temp").add("tmp");
		assertThat(browseDir.getPath()).isEqualTo(expected);
	}

}
