package com.legyver.fenxlib.core.api.context;

import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;

public interface AppStateObserver {
	void observe(LifecyclePhase currentState);
}
