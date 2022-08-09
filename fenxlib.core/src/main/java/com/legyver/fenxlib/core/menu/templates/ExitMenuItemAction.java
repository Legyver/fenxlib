package com.legyver.fenxlib.core.menu.templates;

import com.legyver.fenxlib.core.event.correlation.CorrelatingEventHandlerFactory;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.HookExecutingAction;
import com.legyver.fenxlib.core.lifecycle.hooks.ShutdownHookDecorator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Action to exit the application when invoked.
 * Performs in the following order
 * - Ensures that related actions share the same correlation id
 * - Executes the {@link LifecyclePhase#SHUTDOWN} hook
 * - Calls Platform.exit()
 */
public class ExitMenuItemAction implements EventHandler<ActionEvent> {

	private final EventHandler<ActionEvent> eventHandler;

	/**
	 * Construct an action to handle shutting down the application
	 */
	public ExitMenuItemAction() {
		eventHandler = CorrelatingEventHandlerFactory.wrapIfNecessary(
				new ShutdownHookDecorator(new HookExecutingAction(LifecyclePhase.SHUTDOWN))::execute);
	}

	@Override
	public void handle(ActionEvent event) {
		eventHandler.handle(event);
	}
}
