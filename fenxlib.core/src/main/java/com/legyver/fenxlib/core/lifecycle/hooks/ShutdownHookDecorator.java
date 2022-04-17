package com.legyver.fenxlib.core.lifecycle.hooks;

import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * Decorates an executable hook with a call to Platform.exit()
 */
public class ShutdownHookDecorator extends HookExecutionDecorator {

	/**
	 * Decorate a hook with a call to {@link Platform#exit()}
	 * @param action the hook to decorate
	 */
	public ShutdownHookDecorator(HookExecutingAction action) {
		super(action);
	}

	/**
	 * Execute the hook and shutdown the application
	 */
	public void execute() {
		super.action.execute();
		Platform.exit();
	}

	/**
	 * Execute the hook and shutdown the application
	 * @param event an ignored parameter added to support java-8 function references on streams of ActionEvents
	 */
	public void execute(ActionEvent event) {
		super.action.execute();
		Platform.exit();
	}
}
