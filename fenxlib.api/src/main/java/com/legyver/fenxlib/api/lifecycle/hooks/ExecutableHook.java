package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.core.exception.CoreException;

/**
 * Hook to execute during a lifecycle phase
 */
@FunctionalInterface
public interface ExecutableHook {
	/**
	 * Body of hook to execute
	 * @throws CoreException if there is an error executing the hook
	 */
	void execute() throws CoreException;
}
