package com.legyver.fenxlib.core.impl.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.ConfigService;
import com.legyver.fenxlib.core.api.config.IApplicationConfig;
import com.legyver.fenxlib.core.impl.files.FileIOUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestConfigService implements ConfigService {
	private FileIOUtil fileIOUtil = new FileIOUtil();

	@Override
	public <T extends IApplicationConfig> T loadConfig(String filename) throws CoreException {
		try (InputStream inputStream = TestConfigService.class.getResourceAsStream(filename)) {
			String string = IOUtils.toString(new InputStreamReader(inputStream));
			return (T) fileIOUtil.readModel(string);
		} catch (IOException ex) {
			throw new CoreException("Unable to read resource: " + filename, ex);
		}
	}

	@Override
	public <T extends IApplicationConfig> boolean saveConfig(String filename, T config) throws CoreException {
		return true;//no saving in test
	}

	@Override
	public int order() {
		return 1;
	}

}
