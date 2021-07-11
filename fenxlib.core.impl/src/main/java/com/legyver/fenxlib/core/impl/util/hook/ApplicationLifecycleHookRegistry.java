package com.legyver.fenxlib.core.impl.util.hook;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.context.ApplicationStateMachine;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * An application lifecycle hook registry to keep track of all lifecycle hooks and the application current state
 */
public class ApplicationLifecycleHookRegistry {

	private final EnumMap<LifecyclePhase, TreeMap<Integer, ExecutableHook>> lifecycleHooks = new EnumMap<>(LifecyclePhase.class);
	private final ApplicationStateMachine applicationStateMachine = new ApplicationStateMachine();

	/**
	 * Construct an application lifecycle hook registry to keep track of all lifecycle hooks and the application current state
	 */
	public ApplicationLifecycleHookRegistry() {
		applicationStateMachine.addObserver(ApplicationContext.getAppState());
	}

	/**
	 * Startup the application by running through the lifecycle to {@link LifecyclePhase#POST_INIT}
	 * @throws CoreException if there is an error raised by any of the associated hooks
	 */
	public void startup() throws CoreException {
		executeHook(LifecyclePhase.POST_INIT);
	}

	/**
	 * Execute a lifecycle hook
	 * @param hook the hook (phase) to execute
	 * @throws CoreException if an error is raised in running the hook
	 */
	public void executeHook(LifecyclePhase hook) throws CoreException {
		applicationStateMachine.run(hook, phase -> {
			TreeMap<Integer, ExecutableHook> executableHooks = lifecycleHooks.get(phase);
			if (executableHooks != null) {
				for (Iterator<Integer> it = executableHooks.navigableKeySet().iterator(); it.hasNext(); ) {
					Integer currentPriority = it.next();
					ExecutableHook executableHook = executableHooks.get(currentPriority);
					executableHook.execute();
				}
			}
		});
	}

	/**
	 * Register a lifecycle phase to be executed in a specific lifecycle phase
	 * @param phase the phase in which the hook should be executed
	 * @param executableHook the hook to be executed
	 * @param priority: determines the order in which executable hooks within same phase are run
	 */
	public void registerHook(LifecyclePhase phase, ExecutableHook executableHook, int priority) {
		TreeMap<Integer, ExecutableHook> executableHooks = lifecycleHooks.getOrDefault(phase, new TreeMap<>());
		lifecycleHooks.put(phase, executableHooks);

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
			executableHooks.put(nextAvailable, executableHook);
		}
	}

	/**
	 * Clear all lifecycle hooks and reset the application state machine
	 */
	public void reset() {
		lifecycleHooks.clear();
		applicationStateMachine.reset();
	}
}
