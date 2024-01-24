package com.legyver.fenxlib.api.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Strategy that throws a RuntimeException if receiving a log request of a level less than a specified threshold
 * before the logging system has been initiated.
 *
 * Note: this does not apply to logging events that are Level.OFF
 */
public class RuntimeExceptionLogStrategy extends BaseLogStrategy implements LogHandlingStrategy {
	private final Level threshold;

	/**
	 * Construct a RuntimeExceptionLogStrategy that decorates another {@link LogHandlingStrategy}.
	 * The decorated strategy will be evaluated first.
	 * If no threshold is specified, the default value will be FATAL
	 * @param decoratedStrategy strategy to decorate
	 * @param threshold the maximum threshold at which to throw a RuntimeException.
	 */
	public RuntimeExceptionLogStrategy(LogHandlingStrategy decoratedStrategy, Level threshold) {
		super(decoratedStrategy);
		this.threshold = Objects.requireNonNullElse(threshold, Level.FATAL);
	}

	/**
	 * Construct a RuntimeExceptionLogStrategy
	 * @param threshold the maximum threshold at which to throw a RuntimeException
	 */
	public RuntimeExceptionLogStrategy(Level threshold) {
		this(null, threshold);
	}

	/**
	 * Construct a RuntimeExceptionLogStrategy
	 */
	public RuntimeExceptionLogStrategy() {
		this(null);
	}

	@Override
	public void handlePreBootstrap(Level level, String message, Throwable t, Object...args) {
		super.handlePreBootstrap(level, message, t);
		if (level.intLevel() > Level.OFF.intLevel() && (level.intLevel() <= threshold.intLevel())) {
			throw new UnloggableErrorException("Error received before logging application bootstrapped: " + message, t);
		}
	}

	@Override
	public void handlePostBootstrap(Logger logger, Level level, String message, Throwable t, Object...args) {
		super.handlePostBootstrap(logger, level, message, t, args);
	}
}
