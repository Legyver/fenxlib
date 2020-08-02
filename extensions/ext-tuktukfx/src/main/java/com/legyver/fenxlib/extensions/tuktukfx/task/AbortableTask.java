package com.legyver.fenxlib.extensions.tuktukfx.task;

import com.legyver.fenxlib.extensions.tuktukfx.task.adapter.AbortableTaskStatusAdapter;
import com.legyver.tuktukfx.adapter.JavaFxAdapter;
import com.legyver.tuktukfx.task.ITask;

public class AbortableTask<T> extends JavaFxAdapter<T> implements AbortableTaskStatusAdapter {
	private boolean aborted;

	public AbortableTask(ITask<T> wrappedTask) {
		super(wrappedTask);
	}

	@Override
	public boolean isAborted() {
		return aborted;
	}

	@Override
	public void setAborted(boolean aborted) {
		this.aborted = aborted;
	}
}
