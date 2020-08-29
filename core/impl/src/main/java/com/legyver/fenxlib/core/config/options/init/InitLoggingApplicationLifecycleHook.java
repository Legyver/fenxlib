package com.legyver.fenxlib.core.config.options.init;

import com.legyver.fenxlib.core.config.load.ApplicationHome;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

import java.io.File;

import static com.legyver.fenxlib.core.config.load.ApplicationHome.APP_HOME_SUFFIX;

/**
 *  Sets a system variable of &lt;appname&gt;.home.logging
 *  ie: if appName = "MyApp" would set the following environment variables
 *    myapp.home.logging
 *  in your log4j2.xml you would read this as ${sys:myapp.home.logging}
 *  	&lt;property name="filename"&gt;${sys:myapp.home.logging}/application.log&lt;/property&gt;
 * 		&lt;property name="filenamePattern"&gt;${sys:myapp.home.logging}/application-%d{yyyy-MM-dd}.log.gz
 */
public class InitLoggingApplicationLifecycleHook implements ApplicationLifecycleHook {
	public static final String APP_HOME_LOGGING_SUFFIX = APP_HOME_SUFFIX + ".logging";

	private final ApplicationHome appHome;
	private final String appName;

	public InitLoggingApplicationLifecycleHook(ApplicationHome appHome, String appName) {
		this.appHome = appHome;
		this.appName = appName;
	}

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
