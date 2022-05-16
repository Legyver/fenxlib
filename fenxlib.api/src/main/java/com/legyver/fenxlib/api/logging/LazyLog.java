package com.legyver.fenxlib.api.logging;

import com.legyver.fenxlib.api.context.ApplicationContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lazy initiator of logging.
 *
 * Any errors during the LifecyclePhase.BOOTSTRAP are handled according to the specified strategy
 */
public class LazyLog {
	private final Class<?> klass;
	private LogHandlingStrategy strategy;

	private Logger logger;

	/**
	 * Construct a LazyLog for the specified class
	 * @param klass the class where the logger will be used
	 * @param strategy the strategy to use for handling logging
	 */
	public LazyLog(Class<?> klass, LogHandlingStrategy strategy) {
		this.klass = klass;
		this.strategy = strategy;
	}

	/**
	 * Construct a LazyLog for the specified class.
	 * By default this will use a LogPostBootstrapOnlyLogStrategy decorating a RuntimeExceptionLogStrategy that crashes the application if a FATAL error occurs
	 * @param klass the class where the logger will be used
	 */
	public LazyLog(Class<?> klass) {
		this(klass, new LogPostBootstrapOnlyLogStrategy(new RuntimeExceptionLogStrategy(Level.FATAL)));
	}

	private void log(Level level, String message, Throwable t) {
		if (logger == null) {
			synchronized (this) {
				if (logger == null) {
					if (ApplicationContext.getAppState().isBootstrapped()) {
						logger = LogManager.getLogger(klass);
						strategy.handlePostBootstrap(logger, level, message, t);
					} else {
						strategy.handlePreBootstrap(level, message, t);
						throw new RuntimeException("Message received before logging has been initiated: " + message);
					}
				} else {
					strategy.handlePostBootstrap(logger, level, message, t);
				}
			}
		} else {
			strategy.handlePostBootstrap(logger, level, message, t);
		}
	}

	/**
	 * Log at level all
	 * @param message the message
	 */
	public void all(String message) {
		all(message, null);
	}

	/**
	 * Log at level all
	 * @param message the message
	 * @param t the throwable
	 */
	public void all(String message, Throwable t) {
		log(Level.ALL, message, t);
	}

	/**
	 * Log at level trace
	 * @param message the message
	 */
	public void trace(String message) {
		trace(message, null);
	}

	/**
	 * Log at level trace
	 * @param message the message
	 * @param t the throwable
	 */
	public void trace(String message, Throwable t) {
		log(Level.TRACE, message, t);
	}

	/**
	 * Log at level debug
	 * @param message the message
	 */
	public void debug(String message) {
		debug(message, null);
	}

	/**
	 * Log at level debug
	 * @param message the message
	 * @param t the throwable
	 */
	public void debug(String message, Throwable t) {
		log(Level.DEBUG, message, t);
	}

	/**
	 * Log at level info
	 * @param message the message
	 */
	public void info(String message) {
		info(message, null);
	}

	/**
	 * Log at level info
	 * @param message the message
	 * @param t the throwable
	 */
	public void info(String message, Throwable t) {
		log(Level.INFO, message, t);
	}

	/**
	 * Log at level warn
	 * @param message the message
	 */
	public void warn(String message) {
		warn(message, null);
	}

	/**
	 * Log at level warn
	 * @param message the message
	 * @param t the throwable
	 */
	public void warn(String message, Throwable t) {
		log(Level.WARN, message, t);
	}

	/**
	 * Log at level error
	 * @param message the message
	 */
	public void error(String message) {
		error(message, null);
	}

	/**
	 * Log at level error
	 * @param message the message
	 * @param t the throwable
	 */
	public void error(String message, Throwable t) {
		log(Level.ERROR, message, t);
	}

	/**
	 * Log at level fatal
	 * @param message the message
	 */
	public void fatal(String message) {
		fatal(message, null);
	}

	/**
	 * Log at level fatal
	 * @param message the message
	 * @param t the throwable
	 */
	public void fatal(String message, Throwable t) {
		log(Level.FATAL, message, t);
	}

	/**
	 * Do not log the message
	 * @param message the message
	 */
	public void off(String message) {
		off(message, null);
	}

	/**
	 * Do not log the message
	 * @param message the message
	 * @param t the throwable
	 */
	public void off(String message, Throwable t) {
		log(Level.OFF, message, t);
	}
}
