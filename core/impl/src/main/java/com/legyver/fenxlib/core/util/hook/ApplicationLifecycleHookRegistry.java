package com.legyver.fenxlib.core.util.hook;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.context.ApplicationStateMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.TreeMap;

public class ApplicationLifecycleHookRegistry {
	private static final Logger logger = LogManager.getLogger();

	private final EnumMap<LifecyclePhase, TreeMap<Integer, ExecutableHook>> lifecycleHooks = new EnumMap<>(LifecyclePhase.class);
	private final ApplicationStateMachine applicationStateMachine = new ApplicationStateMachine();

	public ApplicationLifecycleHookRegistry() {
		applicationStateMachine.addObserver(ApplicationContext.getAppState());
	}

	public void executeInitHooks() throws CoreException {
		executeHook(LifecyclePhase.BOOTSTRAP);
		executeHook(LifecyclePhase.PRE_INIT);
		executeHook(LifecyclePhase.INIT);
	}

	public void executeHook(LifecyclePhase hook) throws CoreException {
		if (applicationStateMachine.begin(hook)) {
			TreeMap<Integer, ExecutableHook> executableHooks = lifecycleHooks.get(hook);
			if (executableHooks != null) {
				for (Iterator<Integer> it = executableHooks.navigableKeySet().iterator(); it.hasNext(); ) {
					Integer currentPriority = it.next();
					ExecutableHook executableHook = executableHooks.get(currentPriority);
					if (logger.isDebugEnabled()) {
						logger.trace("Executing hook priority: " + currentPriority);
					}
					executableHook.execute();
				}
			}
			applicationStateMachine.end(hook);
		}
	}

	/**
	 *
	 * @param hook
	 * @param executableHook
	 * @param priority: determines the order in which executable hooks within same phase are run
	 */
	public void registerHook(LifecyclePhase hook, ExecutableHook executableHook, int priority) {
		TreeMap<Integer, ExecutableHook> executableHooks = lifecycleHooks.getOrDefault(hook, new TreeMap<>());
		lifecycleHooks.put(hook, executableHooks);

		if (!executableHooks.containsKey(priority)) {
			executableHooks.put(priority, executableHook);
		} else {
			Integer nextAvailable = Integer.MAX_VALUE;
			for (int i = priority; i < Integer.MAX_VALUE; i++) {
				if (!executableHooks.containsKey(i)) {
					nextAvailable = i;
					break;
				}
			}
			logger.warn("There is already a hook for priority: " + priority + ".  Adding it to next available priority: " + nextAvailable);
			executableHooks.put(nextAvailable, executableHook);
		}
	}

	public void reset() {
		lifecycleHooks.clear();
		applicationStateMachine.reset();
	}
}
