package com.legyver.fenxlib.core.api.util.hook;

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
