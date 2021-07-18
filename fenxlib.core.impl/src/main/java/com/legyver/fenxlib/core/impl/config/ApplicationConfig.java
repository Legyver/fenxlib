package com.legyver.fenxlib.core.impl.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.IApplicationConfig;
import com.legyver.fenxlib.core.api.config.parts.ILastOpened;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyModified;
import com.legyver.utils.mapqua.mapbacked.MapSyncable;

/**
 * Base class for an application config that is syncable to an application config file via MapSyncable
 * Tracks the following
 *   1. Recently modified files
 *   2. Directory of last opened file
 * @param <M> IRecentlyModified MapSyncable data
 * @param <O> ILastOpened MapSyncable data
 */
public abstract class ApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> implements MapSyncable, IApplicationConfig<M, O> {

	/**
	 * Retrieve the "recently-modified" section of the config
	 * @return the "recently-modified" section of the config
	 * @throws CoreException if there is a problem parsing the config
	 */
	abstract public M getRecentlyModified() throws CoreException;

	/**
	 * Retrieve the "last-opened" section of the config
	 * @return the "last-opened" section of the config
	 * @throws CoreException if there is a problem parsing the config
	 */
	abstract public O getLastOpened() throws CoreException;

}
