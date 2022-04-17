package com.legyver.fenxlib.core.lifecycle;

/**
 * Lifecycle phases
 */
public enum LifecyclePhase {
	/**
	 * The first phase, BOOTSTRAP, initializes things like logging and environment variables
	 */
	BOOTSTRAP,
	/**
	 * The second phase, PRE_INIT, involves things like loading config files
	 */
	PRE_INIT,
	/**
	 * The third phase, INIT, involves any initialization tasks that need to be done, now we have logging and config files loaded
	 */
	INIT,
	/**
	 * The fourth phase, POST_INIT, placeholder.  The last phase of the application startup lifecycle
	 */
	POST_INIT,
	/**
	 * Any hooks that need to be executed before the application shuts down, like updating settings or configuration
	 */
	PRE_SHUTDOWN,
	/**
	 * Final hooks like shutting down thread pools gracefully, etc
	 */
	SHUTDOWN;
}
