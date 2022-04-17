package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.lifecycle.LifecyclePhase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Executes a lifecycle hook, logging any checked exception.
 *
 * The intent here is to avoid having to have try-catch logic whenever assigning a function to a JavaFX widget.
 */
public class HookExecutingAction {
	private static final Logger logger = LogManager.getLogger(HookExecutingAction.class);
	private final LifecyclePhase hook;

	/**
	 * Construct a action that executes a lifecycle hook
	 * @param hook the hook to execute
	 */
	public HookExecutingAction(LifecyclePhase hook) {
		this.hook = hook;
	}

	/**
	 * Execute a lifecycle hook
	 */
	public void execute() {
		try {
			ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(hook);
		} catch (Exception ex) {
			logger.error("Error executing hook: " + hook.name(), ex);
		}
	}
}
