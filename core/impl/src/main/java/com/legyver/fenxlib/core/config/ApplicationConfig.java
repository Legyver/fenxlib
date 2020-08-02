package com.legyver.fenxlib.core.config;

import com.legyver.fenxlib.core.config.parts.ILastOpened;
import com.legyver.fenxlib.core.config.parts.IRecentlyModified;
import com.legyver.util.mapqua.mapbacked.MapSyncable;

public abstract class ApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> implements MapSyncable {

	abstract public M getRecentlyModified();

	abstract public O getLastOpened();

}
