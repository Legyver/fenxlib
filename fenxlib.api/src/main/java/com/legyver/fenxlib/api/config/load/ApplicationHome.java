package com.legyver.fenxlib.api.config.load;

import com.legyver.fenxlib.api.files.util.LazyCreateDirectoryWrapper;

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
public class ApplicationHome {
	/**
	 * suffix used in setting up logging
	 * ie: (src/main/resources/log4j2.xml)
	 *  &lt;properties&gt;
	 *         &lt;property name="patternlayout"&gt;%d{dd/MM/yyyy HH:mm:ss} %5p %c{1}:%L - %m%n&lt;/property&gt;
	 *         &lt;property name="filename"&gt;${sys: &lt;appName&gt;.home.logging}/application.log&lt;/property&gt;
	 *         &lt;property name="filenamePattern"&gt;${sys: &lt;appName&gt;.home.logging}/application-%d{yyyy-MM-dd}.log.gz&lt;/property&gt;
	 *  &lt;/properties&gt;
	 */
	public static final String APP_HOME_SUFFIX = ".home";
	private final LazyCreateDirectoryWrapper appHome;
	private final LazyCreateDirectoryWrapper cacheDirectory;
	private final LazyCreateDirectoryWrapper configDirectory;
	private final LazyCreateDirectoryWrapper logDirectory;

	/**
	 * Construct an Application Home based on the provided app name.
	 * - Creates a directory in the appropriate location for all config files, cached files, and log files for the application to be saved.
	 * -- In Windows this is under %AppData%
	 * -- For other OS's it will be under user.home
	 * @param appName the name of the application.
	 */
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
	 * @return the application home directory
	 */
	public File getAppHome() {
		return appHome.getDirectory();
	}

	/**
	 * Get the cache directory where all the files cached by the application are kept
	 * @return the cache directory
	 */
	public File getCacheDirectory() {
		return cacheDirectory.getDirectory();
	}

	/**
	 * Get the config directory where all the application config files are kept
	 * @return the config directory
	 */
	public File getConfigDirectory() {
		return configDirectory.getDirectory();
	}

	/**
	 * Get the log directory where all the application log files are kept
	 * @return the logging directory
	 */
	public File getLogDirectory() {
		return logDirectory.getDirectory();
	}

	/**
	 * Load a file from the application cache
	 * @param name the name of the file
	 * @return the cached file
	 */
	public File getCacheFile(String name) {
		return cacheDirectory.loadFileFromDir(name);
	}
}
