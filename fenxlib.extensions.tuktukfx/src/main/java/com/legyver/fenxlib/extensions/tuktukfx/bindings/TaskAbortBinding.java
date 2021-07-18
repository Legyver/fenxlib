package com.legyver.fenxlib.extensions.tuktukfx.bindings;

import com.legyver.fenxlib.core.api.context.BaseApplicationContext;
import com.legyver.fenxlib.core.api.locator.query.bindings.BaseBinding;
import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;

/**
 * Set the abort flag on any incomplete tasks when the application shuts down
 */
public class TaskAbortBinding extends BaseBinding {

	/**
	 * Register the task with the application so application shutdown aborts the task
	 * @param abortable the task to abort on application shutdown
	 */
	public static void taskAbortObservesShutdown(AbortableTaskStatusAdapter abortable) {
		BaseApplicationContext.getAppState().shuttingDownProperty().addListener((observableValue, oldValue, newValue) -> {
			abortable.abort();
		});
	}
}
