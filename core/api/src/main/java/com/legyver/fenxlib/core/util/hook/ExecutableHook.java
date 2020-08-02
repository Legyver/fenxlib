package com.legyver.fenxlib.core.util.hook;

import com.legyver.core.exception.CoreException;

@FunctionalInterface
public interface ExecutableHook {
	void execute() throws CoreException;
}
