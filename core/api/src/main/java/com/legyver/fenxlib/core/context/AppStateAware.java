package com.legyver.fenxlib.core.context;

public interface AppStateAware {

	default BindableAppState getAppState() {
		return BaseApplicationContext.getAppState();
	}
}
