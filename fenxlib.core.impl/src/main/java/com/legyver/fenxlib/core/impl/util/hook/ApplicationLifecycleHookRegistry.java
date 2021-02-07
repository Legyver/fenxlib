package com.legyver.fenxlib.core.impl.util.hook;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.context.ApplicationStateMachine;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.TreeMap;

public class ApplicationLifecycleHookRegistry {

	private final EnumMap<LifecyclePhase, TreeMap<Integer, ExecutableHook>> lifecycleHooks = new EnumMap<>(LifecyclePhase.class);
	private final ApplicationStateMachine applicationStateMachine = new ApplicationStateMachine();

	public ApplicationLifecycleHookRegistry() {
		applicationStateMachine.addObserver(ApplicationContext.getAppState());
	}

	public void startup() throws CoreException {
		executeHook(LifecyclePhase.POST_INIT);
	}

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
			executableHooks.put(nextAvailable, executableHook);
		}
	}

	public void reset() throws CoreException {
		lifecycleHooks.clear();
		applicationStateMachine.reset();
	}
}
