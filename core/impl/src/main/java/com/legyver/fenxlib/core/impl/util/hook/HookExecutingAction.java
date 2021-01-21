package com.legyver.fenxlib.core.impl.util.hook;

import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
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

	public HookExecutingAction(LifecyclePhase hook) {
		this.hook = hook;
	}

	public void execute() {
		try {
			ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(hook);
		} catch (Exception ex) {
			logger.error("Error executing hook: " + hook.name(), ex);
		}
	}
}
