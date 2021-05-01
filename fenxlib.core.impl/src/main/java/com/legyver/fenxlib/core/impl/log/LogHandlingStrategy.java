package com.legyver.fenxlib.core.impl.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Strategy to encapsulate logging scenarios
 *  Before logging has been initiated during bootstrap
 *  After logging has been initialed during bootstrap
 */
public interface LogHandlingStrategy {
	/**
	 * Handle log messages before logging has been initiated
	 * @param level the level to log at
	 * @param message the message to log
	 * @param t an optional throwable
	 */
	void handlePreBootstrap(Level level, String message, Throwable t);

	/**
	 * Handle log messages after logging has been initiated
	 * @param logger the class logger to use
	 * @param level the level to log at
	 * @param message the message to log
	 * @param t an optional throwable
	 */
	void handlePostBootstrap(Logger logger, Level level, String message, Throwable t);
}
