package com.legyver.fenxlib.api.lifecycle;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import javafx.application.Application;
import javafx.stage.Stage;

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
     * @deprecated Use {@link com.legyver.fenxlib.api.config.options.ApplicationOptions#startup(Application, Stage)}
     */
    @Deprecated
    void startup() throws CoreException;

    /**
     * Register a lifecycle phase to be executed in a specific lifecycle phase
     * @param lifecyclePhase the phase in which the hook should be executed
     * @param executableHook the hook to be executed
     * @param priority: determines the order in which executable hooks within same phase are run
     */
    void registerHook(LifecyclePhase lifecyclePhase, ExecutableHook executableHook, int priority);

    /**
     * Delay shutdown for at least this number of milliseconds
     * If this is called with different values, it shall always save the latest one
     * @param delayInMillis the delay in milliseconds
     */
    void delayShutdown(long delayInMillis);
}
