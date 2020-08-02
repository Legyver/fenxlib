package com.legyver.fenxlib.extensions.tuktukfx.task.exec;

import com.legyver.fenxlib.extensions.tuktukfx.task.adapter.AbortableTaskStatusAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum TaskExecutor {
	INSTANCE;
	private static final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public void submitTask(AbortableTaskStatusAdapter abortableTask) {
		pool.submit(abortableTask);
	}

	public void shutdownNow() {
		pool.shutdownNow();
	}
}
