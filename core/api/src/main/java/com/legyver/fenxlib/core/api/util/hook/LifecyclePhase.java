package com.legyver.fenxlib.core.api.util.hook;

public enum LifecyclePhase {
	BOOTSTRAP, //things like init logging env variables
	PRE_INIT,
	INIT,
	POST_INIT,
	PRE_SHUTDOWN;
}
