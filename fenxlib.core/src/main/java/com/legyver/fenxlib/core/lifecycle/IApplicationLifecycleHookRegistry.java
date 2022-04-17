package com.legyver.fenxlib.core.lifecycle;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.lifecycle.hooks.ExecutableHook;

/**
 * Registry of application hooks
 */
public interface IApplicationLifecycleHookRegistry {
    /**
     * Execute actions associated with a particular lifecycle phase
     * @param lifecyclePhase the life cycle phase
     * @throws CoreException if there is an error executing a hook
     */
    void executeHook(LifecyclePhase lifecyclePhase) throws CoreException;

    /**
     * Startup the application by running through the lifecycle to {@link LifecyclePhase#POST_INIT}
     * @throws CoreException if there is an error raised by any of the associated hooks
     */
    void startup() throws CoreException;

    /**
     * Register a lifecycle phase to be executed in a specific lifecycle phase
     * @param lifecyclePhase the phase in which the hook should be executed
     * @param executableHook the hook to be executed
     * @param priority: determines the order in which executable hooks within same phase are run
     */
    void registerHook(LifecyclePhase lifecyclePhase, ExecutableHook executableHook, int priority);
}
