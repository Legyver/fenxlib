package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.fenxlib.api.lifecycle.hooks.HookExecutingAction;

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
