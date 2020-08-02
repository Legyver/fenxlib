package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.core.config.options.mixins.HookRegistrationMixin;
import com.legyver.fenxlib.extensions.tuktukfx.task.exec.TaskExecutor;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

public class TaskExecutorShutdownHook implements HookRegistrationMixin {
	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_SHUTDOWN;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> TaskExecutor.INSTANCE.shutdownNow();//shutdown thread-pool;
	}
}
