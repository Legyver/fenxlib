package com.legyver.fenxlib.core.lifecycle;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.ResettableApplicationLifecycleHookRegistry;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.fenxlib.core.context.ApplicationStateMachine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * An application lifecycle hook registry to keep track of all lifecycle hooks and the application current state
 */
public class ApplicationLifecycleHookRegistry implements ResettableApplicationLifecycleHookRegistry {
	private static final LazyLog lazyLog = new LazyLog(ApplicationLifecycleHookRegistry.class);

	private final EnumMap<LifecyclePhase, TreeMap<Integer, ExecutableHook>> lifecycleHooks = new EnumMap<>(LifecyclePhase.class);
	private final ApplicationStateMachine applicationStateMachine = new ApplicationStateMachine();

	private long delayInMillis = -1;

	/**
	 * Construct an application lifecycle hook registry to keep track of all lifecycle hooks and the application current state
	 */
	public ApplicationLifecycleHookRegistry() {
		applicationStateMachine.addObserver(ApplicationContext.getAppState());
	}

	/**
	 * Startup the application by running through the lifecycle to {@link LifecyclePhase#POST_INIT}
	 * @throws CoreException if there is an error raised by any of the associated hooks
	 * @deprecated Use {@link com.legyver.fenxlib.api.config.options.ApplicationOptions#startup(Application, Stage)}
	 */
	@Deprecated(since = "3.0.1")
	@Override
	public void startup() throws CoreException {
		executeHook(LifecyclePhase.POST_INIT);
	}

	/**
	 * Execute a lifecycle hook
	 * @param hook the hook (phase) to execute
	 * @throws CoreException if an error is raised in running the hook
	 */
	@Override
	public void executeHook(LifecyclePhase hook) throws CoreException {
		applicationStateMachine.run(hook, phase -> {
			if (phase == LifecyclePhase.SHUTDOWN) {
				lazyLog.trace("Setting shutdown flag");
				ApplicationContext.getAppState().setShuttingDown(true);
				if (delayInMillis > 0) {
					try {
						Thread.sleep(delayInMillis);
						lazyLog.trace("Setting shutdown flag: done");
					} catch (InterruptedException e) {
						lazyLog.error("Error setting shutdown hook", e);
						Thread.currentThread().interrupt();
					}
				}
			}

			executePhaseHooks(phase);

			if (phase == LifecyclePhase.SHUTDOWN) {
				lazyLog.trace("Shutting down platform");
				Platform.exit();
			}
		});
	}

	private synchronized void executePhaseHooks(LifecyclePhase phase) throws CoreException {
		lazyLog.trace("Executing lifecycle hooks for phase: {}", phase);
		TreeMap<Integer, ExecutableHook> executableHooks = lifecycleHooks.get(phase);
		if (executableHooks != null) {
			List<Integer> keys = executableHooks.navigableKeySet().stream().collect(Collectors.toUnmodifiableList());
			if (lazyLog.isTraceEnabled()) {
				lazyLog.trace("{} lifecycle hooks found for phase: {}", executableHooks.size(), keys.stream()
						.map(Object::toString)
						.collect(Collectors.joining(","))
				);
			}
			for (Integer currentPriority : keys) {
				lazyLog.trace("Executing lifecycle hook for priority: {}", currentPriority);
				ExecutableHook executableHook = executableHooks.get(currentPriority);
				lazyLog.trace("Executing lifecycle hook class [{}}]", executableHook.getClass());
				executableHook.execute();
				lazyLog.trace("Lifecycle hook [{}] executed", executableHook.getClass());
			}
		}
	}

	/**
	 * Register a lifecycle phase to be executed in a specific lifecycle phase
	 * @param phase the phase in which the hook should be executed
	 * @param executableHook the hook to be executed
	 * @param priority: determines the order in which executable hooks within same phase are run
	 */
	@Override
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

	@Override
	public void delayShutdown(long delayInMillis) {
		if (delayInMillis > this.delayInMillis) {
			this.delayInMillis = delayInMillis;
		}
	}

	/**
	 * Clear all lifecycle hooks and reset the application state machine
	 */
	@Override
	public void reset() {
		lifecycleHooks.clear();
		applicationStateMachine.reset();
	}
}
