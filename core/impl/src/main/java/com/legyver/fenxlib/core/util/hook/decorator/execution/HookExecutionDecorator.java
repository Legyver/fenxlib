package com.legyver.fenxlib.core.util.hook.decorator.execution;

import com.legyver.fenxlib.core.util.hook.HookExecutingAction;

/**
 * Base class for HookExecutingAction decorators
 */
public abstract class HookExecutionDecorator {
	protected final HookExecutingAction action;

	protected HookExecutionDecorator(HookExecutingAction action) {
		this.action = action;
	}
}
