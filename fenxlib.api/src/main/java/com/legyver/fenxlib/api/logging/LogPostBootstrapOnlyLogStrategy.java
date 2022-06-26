package com.legyver.fenxlib.api.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Strategy that only logs messages after the logging system has been initiated
 */
public class LogPostBootstrapOnlyLogStrategy extends BaseLogStrategy implements LogHandlingStrategy {

	/**
	 * Construct a LogPostBootstrapOnlyLogStrategy that decorates another strategy
	 * @param decoratedStrategy the strategy to decorate
	 */
	public LogPostBootstrapOnlyLogStrategy(LogHandlingStrategy decoratedStrategy) {
		super(decoratedStrategy);
	}

	/**
	 * Construct a LogPostBootstrapOnlyLogStrategy
	 */
	public LogPostBootstrapOnlyLogStrategy() {
		this(null);
	}

	@Override
	public void handlePreBootstrap(Level level, String message, Throwable t, Object...args) {
		//noop
	}

	@Override
	public void handlePostBootstrap(Logger logger, Level level, String message, Throwable t, Object...args) {
		logMessage(logger, level, message, t);
	}
}
