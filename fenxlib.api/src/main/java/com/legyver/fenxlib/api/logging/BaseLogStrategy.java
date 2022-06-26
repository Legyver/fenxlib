package com.legyver.fenxlib.api.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	protected void logMessage(Logger logger, Level level, String message, Throwable t, Object...args) {
		if (logger.isEnabled(level)) {
			if (t != null) {
				message = manuallyResolveParameters(message, args);
				logger.log(level, message, t);
			} else {
				logger.log(level, message, args);
			}
		}
	}

	/**
	 * Utility method to manually resolve parameters outside of the logging subsystem.
	 * This is mainly used when
	 * - Throwables are used in conjunction with varargs
	 * - The message is not going to be logged, and we need to resolve the message another way
	 * @param message the message with or without placeholders ("{}")
	 * @param args any args that may need to be resolved
	 * @return the resolved message
	 */
	protected String manuallyResolveParameters(String message, Object[] args) {
		//manually resolve placeholders
		if (args != null) {
			if (message == null) {
				message = Stream.of(args)
						.map(Object::toString)
						.collect(Collectors.joining(", "));
			} else {
				int i = 0;
				while (message.contains("{}")) {
					message = message.replace("{}", args[i++].toString());
				}
			}
		}
		return message;
	}

	@Override
	public void handlePreBootstrap(Level level, String message, Throwable t, Object...args) {
		if (decoratedStrategy != null) {
			decoratedStrategy.handlePreBootstrap(level, message, t);
		}
	}

	@Override
	public void handlePostBootstrap(Logger logger, Level level, String message, Throwable t, Object...args) {
		if (decoratedStrategy != null) {
			decoratedStrategy.handlePostBootstrap(logger, level, message, t);
		}
	}
}
