package com.legyver.fenxlib.core.impl.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Decorator pattern for chaining multiple strategies;
 */
public class BaseLogStrategy implements LogHandlingStrategy {
	private final LogHandlingStrategy decoratedStrategy;

	/**
	 * Decorate the LogHandlingStrategy
	 * @param decoratedStrategy the strategy to decorate.
	 */
	public BaseLogStrategy(LogHandlingStrategy decoratedStrategy) {
		this.decoratedStrategy = decoratedStrategy;
	}

	/**
	 * Decorate the LogHandlingStrategy
	 */
	public BaseLogStrategy() {
		this(null);
	}

	/**
	 * Log a message
	 * @param logger the logger to use
	 * @param level the level to log the message at
	 * @param message the message to log
	 * @param t any throwable
	 */
	protected void logMessage(Logger logger, Level level, String message, Throwable t) {
		if (logger.isEnabled(level)) {
			if (t != null) {
				logger.log(level, message, t);
			} else {
				logger.log(level, message);
			}
		}
	}

	@Override
	public void handlePreBootstrap(Level level, String message, Throwable t) {
		if (decoratedStrategy != null) {
			decoratedStrategy.handlePreBootstrap(level, message, t);
		}
	}

	@Override
	public void handlePostBootstrap(Logger logger, Level level, String message, Throwable t) {
		if (decoratedStrategy != null) {
			decoratedStrategy.handlePostBootstrap(logger, level, message, t);
		}
	}
}
