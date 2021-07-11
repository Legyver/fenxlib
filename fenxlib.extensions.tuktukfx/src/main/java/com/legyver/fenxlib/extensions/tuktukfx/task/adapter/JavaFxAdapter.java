package com.legyver.fenxlib.extensions.tuktukfx.task.adapter;

import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;
import com.legyver.tuktukfx.task.IAbortTask;
import com.legyver.tuktukfx.task.ITask;
import javafx.concurrent.Task;

/**
 * Adapter to process {@link javafx.concurrent.Task} tasks with tuktukfx
 * @param <R> the return type of the task
 */
public class JavaFxAdapter<R extends Object> extends Task<R> implements AbortableTaskStatusAdapter {
	private final ITask<R> wrappedTask;

	/**
	 * Construct a JavaFxAdapter that wraps a TukTukFX {@link ITask}
	 * @param wrappedTask the task to wrap
	 */
	public JavaFxAdapter(ITask<R> wrappedTask) {
		this.wrappedTask = wrappedTask;
	}

	/**
	 * Execute the wrapped task
	 * @return the result
	 * @throws Exception if an Exception is raised by the wrapped task
	 */
	@Override
	protected R call() throws Exception {
		return wrappedTask.execute(this);
	}

	/**
	 * Update the title property
	 * @param message the new title
	 */
	@Override
	public void updateTitle(String message) {
		super.updateTitle(message);//overridden to expose JavaFx messaging to common ProgressAwareTask abstraction
	}

	/**
	 * Update the message property
	 * @param message the new message
	 */
	@Override
	public void updateMessage(String message) {
		super.updateMessage(message);//overridden to expose JavaFx messaging to common TukTukFX task abstraction
	}

	/**
	 * Update the progress indicator
	 * @param numerator the range value
	 * @param denominator the domain value
	 */
	@Override
	public void updateProgress(double numerator, double denominator) {
		super.updateProgress(numerator, denominator);//overridden to expose JavaFx messaging to common TukTukFX task abstraction
	}

	/**
	 * Abort the task
	 */
	@Override
	public void abort() {
		if (wrappedTask instanceof IAbortTask && !super.isDone()) {
			((IAbortTask) wrappedTask).abort();
		}
	}

	/**
	 * Check if the wrapped task is aborted
	 * @return true if the wrapped task is aborted
	 */
	@Override
	public boolean isAborted() {
		if (wrappedTask instanceof IAbortTask) {
			return ((IAbortTask<R>) wrappedTask).isAborted();
		}
		return false;
	}
}
