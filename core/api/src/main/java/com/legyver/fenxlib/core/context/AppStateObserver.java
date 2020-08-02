package com.legyver.fenxlib.core.context;

import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

public interface AppStateObserver {
	void observe(LifecyclePhase currentState);
}
