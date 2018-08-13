package com.legyver.fenxlib.config;

import com.legyver.fenxlib.config.parts.ILastOpened;
import com.legyver.fenxlib.config.parts.IRecentlyModified;
import com.legyver.util.mapqua.mapbacked.MapSyncable;
import com.legyver.util.mapqua.mapbacked.RawMapAware;

public abstract class ApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> implements MapSyncable {

	abstract public M getRecentlyModified();

	abstract public O getLastOpened();

}
