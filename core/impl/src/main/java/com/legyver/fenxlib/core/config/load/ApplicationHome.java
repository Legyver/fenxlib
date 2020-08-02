package com.legyver.fenxlib.core.config.load;

import com.legyver.fenxlib.core.files.LazyCreateDirectoryWrapper;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Where to save internal application files.
 *	Windows: %APPDATA%\<appName>
 *	Others: user.home/.<appName>
 * 
 * Default root directories include
 *   cache
 *   config
 *   logs
 */
public class ApplicationHome implements ApplicationConfigProvider {
	public static final String APP_HOME_SUFFIX = ".home";
	private final LazyCreateDirectoryWrapper appHome;
	private final LazyCreateDirectoryWrapper cacheDirectory;
	private final LazyCreateDirectoryWrapper configDirectory;
	private final LazyCreateDirectoryWrapper logDirectory;

	public ApplicationHome(String appName) {
		String osName = System.getProperty("os.name");
		String appDir;
		if (osName != null && osName.startsWith("Windows")) {
			String appData = System.getenv("APPDATA");
			appDir = appData + File.separator + appName;
		} else {
			appDir = System.getProperty("user.home") + File.separator + appName;
		}
		appHome = new LazyCreateDirectoryWrapper(new File(appDir));
		cacheDirectory = new LazyCreateDirectoryWrapper(new File(appHome.getAbsolutePath() + File.separator + "cache"));
		configDirectory = new LazyCreateDirectoryWrapper(new File(appHome.getAbsolutePath() + File.separator + "config"));
		logDirectory = new LazyCreateDirectoryWrapper(new File(appHome.getAbsolutePath() + File.separator + "logs"));
	}

	public File getAppHome() {
		return appHome.getDirectory();
	}

	public File getCacheDirectory() {
		return cacheDirectory.getDirectory();
	}

	public File getConfigDirectory() {
		return configDirectory.getDirectory();
	}

	public File getLogDirectory() {
		return logDirectory.getDirectory();
	}

	public File getConfigFile(String name) {
		return configDirectory.loadFileFromDir(name);
	}


	public File getCacheFile(String name) {
		return cacheDirectory.loadFileFromDir(name);
	}

	@Override
	public String getApplicationConfigAsString() throws IOException {
		File configFile = getApplicationConfigFile();
		if (configFile.exists()) {
			try {
				return IOUtils.toString(new FileInputStream(configFile), StandardCharsets.UTF_8);
			} catch (FileNotFoundException e) {
				//can't happen see configFile.exists()
				throw new UnsupportedOperationException("Can't open a file that does not exist");
			}
		}
		return null;
	}

	@Override
	public File getApplicationConfigFile() {
		return getConfigFile("config.json");
	}
}
