package com.legyver.fenxlib.core.context;

import com.legyver.fenxlib.core.lifecycle.LifecyclePhase;

/**
 * Observer notified when there is a change in the {@link LifecyclePhase} of the ApplicationState
 */
public interface AppStateObserver {
	/**
	 * Notify the observer of the lifecycle change
	 * @param currentState the new state of the application
	 */
	void observe(LifecyclePhase currentState);
}
