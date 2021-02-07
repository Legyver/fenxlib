package com.legyver.fenxlib.extensions.tuktukfx.task.adapter;

import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;
import com.legyver.tuktukfx.task.IAbortTask;
import com.legyver.tuktukfx.task.ITask;
import javafx.concurrent.Task;

public class JavaFxAdapter<R extends Object> extends Task<R> implements AbortableTaskStatusAdapter {
	private final ITask<R> wrappedTask;

	public JavaFxAdapter(ITask<R> wrappedTask) {
		this.wrappedTask = wrappedTask;
	}

	@Override
	protected R call() throws Exception {
		return wrappedTask.execute(this);
	}

	@Override
	public void updateTitle(String message) {
		super.updateTitle(message);//overridden to expose JavaFx messaging to common ProgressAwareTask abstraction
	}

	@Override
	public void updateMessage(String message) {
		super.updateMessage(message);//overridden to expose JavaFx messaging to common ProgressAwareTask abstraction
	}

	@Override
	public void updateProgress(double numerator, double denominator) {
		super.updateProgress(numerator, denominator);//overridden to expose JavaFx messaging to common ProgressAwareTask abstraction
	}

	@Override
	public void abort() {
		if (wrappedTask instanceof IAbortTask && !super.isDone()) {
			((IAbortTask) wrappedTask).abort();
		}
	}

	@Override
	public boolean isAborted() {
		if (wrappedTask instanceof IAbortTask) {
			return ((IAbortTask<R>) wrappedTask).isAborted();
		}
		return false;
	}
}
