package com.legyver.fenxlib.core.api.util.hook;

import com.legyver.core.exception.CoreException;

@FunctionalInterface
public interface ExecutableHook {
	void execute() throws CoreException;
}
