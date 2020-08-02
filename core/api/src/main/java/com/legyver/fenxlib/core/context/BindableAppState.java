package com.legyver.fenxlib.core.context;

import com.legyver.fenxlib.core.util.hook.LifecyclePhase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BindableAppState implements AppStateObserver {
	private final BooleanProperty shuttingDown = new SimpleBooleanProperty(false);

	public boolean isShuttingDown() {
		return shuttingDown.get();
	}

	public BooleanProperty shuttingDownProperty() {
		return shuttingDown;
	}

	public void setShuttingDown(boolean shuttingDown) {
		this.shuttingDown.set(shuttingDown);
	}

	@Override
	public void observe(LifecyclePhase currentState) {
		switch (currentState) {
			case PRE_SHUTDOWN: shuttingDown.set(true); break;
			default:
		}
	}
}
