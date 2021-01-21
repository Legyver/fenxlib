package com.legyver.fenxlib.core.impl.util.hook.decorator.execution;

import com.legyver.fenxlib.core.impl.util.hook.HookExecutingAction;
import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * Decorates an executable hook with a call to Platform.exit()
 */
public class ShutdownHookDecorator extends HookExecutionDecorator {

	public ShutdownHookDecorator(HookExecutingAction action) {
		super(action);
	}

	public void execute() {
		super.action.execute();
		Platform.exit();
	}

	public void execute(ActionEvent event) {
		super.action.execute();
		Platform.exit();
	}
}
