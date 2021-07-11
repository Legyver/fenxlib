package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.config.load.ApplicationHome;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;

import java.io.File;

/**
 *  Sets a system variable of &lt;appname&gt;.home.logging
 *  ie: if appName = "MyApp" would set the following environment variables
 *    myapp.home.logging
 *  in your log4j2.xml you would read this as ${sys:myapp.home.logging}
 *  	&lt;property name="filename"&gt;${sys:myapp.home.logging}/application.log&lt;/property&gt;
 * 		&lt;property name="filenamePattern"&gt;${sys:myapp.home.logging}/application-%d{yyyy-MM-dd}.log.gz
 */
public class InitLoggingApplicationLifecycleHook implements ApplicationLifecycleHook {
	/**
	 * The suffix for the system variable that determines where log files go
	 */
	public static final String APP_HOME_LOGGING_SUFFIX = ApplicationHome.APP_HOME_SUFFIX + ".logging";

	private final ApplicationHome appHome;
	private final String appName;

	/**
	 * Construct an application lifecycle hook to init logging
	 * @param appHome the application home where all configs, logs, etc are stored
	 * @param appName the simple name of the directory the application stores its files.
	 */
	public InitLoggingApplicationLifecycleHook(ApplicationHome appHome, String appName) {
		this.appHome = appHome;
		this.appName = appName;
	}

	/**
	 * Construct an application hook to init logging.
	 * This sets up the logging location in the {@link ApplicationHome}
	 * @param appName the simple name of the directory the application stores its files.
	 */
	public InitLoggingApplicationLifecycleHook(String appName) {
		this(new ApplicationHome(appName), appName);
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.BOOTSTRAP;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			//below lazily creates the log-directory if it didn't exist already
			File logDirectory = appHome.getLogDirectory();
			//set a system variable to be read for logging
			System.setProperty(appName.toLowerCase() + APP_HOME_LOGGING_SUFFIX, logDirectory.getAbsolutePath());
		};
	}

}
