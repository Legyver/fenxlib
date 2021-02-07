package com.legyver.fenxlib.extensions.tuktukfx.bindings;

import com.legyver.fenxlib.core.api.context.BaseApplicationContext;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingFactory;
import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;

/**
 * Set the abort flag on any incomplete tasks when the application shuts down
 */
public interface TaskAbortBindingFactory extends AbstractBindingFactory {

	default void taskAbortObservesShutdown(AbortableTaskStatusAdapter abortable) {
		BaseApplicationContext.getAppState().shuttingDownProperty().addListener((observableValue, oldValue, newValue) -> {
			abortable.abort();
		});
	}
}