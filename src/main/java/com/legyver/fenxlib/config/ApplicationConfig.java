package com.legyver.fenxlib.config;

import com.legyver.fenxlib.config.parts.ILastOpened;
import com.legyver.fenxlib.config.parts.IRecentlyModified;

public abstract class ApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> implements RawMapAware {

	abstract public M getRecentlyModified();
	abstract public void setRecentlyModified(M recentlyModified);

	abstract public O getLastOpened();
	abstract public void setLastOpened(O lastOpened);

}
