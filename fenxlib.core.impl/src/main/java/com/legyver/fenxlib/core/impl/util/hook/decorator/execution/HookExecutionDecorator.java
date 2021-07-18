package com.legyver.fenxlib.core.impl.util.hook.decorator.execution;

import com.legyver.fenxlib.core.impl.util.hook.HookExecutingAction;

/**
 * Base class for HookExecutingAction decorators
 */
public abstract class HookExecutionDecorator {
	/**
	 * The decorated action
	 */
	protected final HookExecutingAction action;

	/**
	 * Construct a decorator for the given action
	 * @param action the action to be decorated
	 */
	protected HookExecutionDecorator(HookExecutingAction action) {
		this.action = action;
	}
}
