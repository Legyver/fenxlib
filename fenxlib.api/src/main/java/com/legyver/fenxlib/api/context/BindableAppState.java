package com.legyver.fenxlib.api.context;

import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Exposes the application state to anyone who to stay informed about these things
 */
public class BindableAppState implements AppStateObserver {
	/**
	 * Monitors if the application is shutting down
	 */
	private final BooleanProperty shuttingDown = new SimpleBooleanProperty(false);
	/**
	 * Monitors if the application has been successfully bootstrapped
	 */
	private final BooleanProperty bootstrapped = new SimpleBooleanProperty(false);

	/**
	 * Check if the application is bootstrapped
	 * @return true if the application is bootstrapped
	 */
	public boolean isBootstrapped() {
		return bootstrapped.get();
	}

	/**
	 * Check if the application is shutting down
	 * @return true if the application is shutting down
	 */
	public boolean isShuttingDown() {
		return shuttingDown.get();
	}

	/**
	 * Allow applications to bind to the shuttingDown property
	 * @return the shutting down property
	 */
	public BooleanProperty shuttingDownProperty() {
		return shuttingDown;
	}

	/**
	 * Set the shutting down flag
	 * @param shuttingDown the value to set it to
	 */
	public void setShuttingDown(boolean shuttingDown) {
		this.shuttingDown.set(shuttingDown);
	}

	/**
	 * Observe a change in application state
	 * @param currentState the new state of the application
	 */
	@Override
	public void observe(LifecyclePhase currentState) {
		switch (currentState) {
			case PRE_SHUTDOWN: shuttingDown.set(true); break;
			case BOOTSTRAP: bootstrapped.set(true); break;
			default:
		}
	}
}
