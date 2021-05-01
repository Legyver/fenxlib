package com.legyver.fenxlib.core.impl.config.load;

import com.legyver.fenxlib.core.impl.files.LazyCreateDirectoryWrapper;

import java.io.File;

/**
 * Where to save internal application files.
 *	Windows: %APPDATA%\&lt;appName&gt;
 *	Others: user.home/.&lt;appName&gt;
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

	public File getCacheFile(String name) {
		return cacheDirectory.loadFileFromDir(name);
	}

	@Override
	public String getApplicationConfigFilename() {
		return configDirectory.getFullFilename("config.json");
	}
}
