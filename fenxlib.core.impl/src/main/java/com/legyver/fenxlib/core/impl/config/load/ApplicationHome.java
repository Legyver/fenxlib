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

	/**
	 * Get the Application Home directory where all managed application files are kept.
	 * @return
	 */
	public File getAppHome() {
		return appHome.getDirectory();
	}

	/**
	 * Get the cache directory where all the files cached by the application are kept
	 * @return
	 */
	public File getCacheDirectory() {
		return cacheDirectory.getDirectory();
	}

	/**
	 * Get the config directory where all the application config files are kept
	 * @return
	 */
	public File getConfigDirectory() {
		return configDirectory.getDirectory();
	}

	/**
	 * Get the log directory where all the application log files are kept
	 * @return the directory
	 */
	public File getLogDirectory() {
		return logDirectory.getDirectory();
	}

	/**
	 * Load a file from the application cache
	 * @param name the name of the file
	 * @return the file
	 */
	public File getCacheFile(String name) {
		return cacheDirectory.loadFileFromDir(name);
	}

	@Override
	public String getApplicationConfigFilename() {
		return configDirectory.getFullFilename("config.json");
	}
}
