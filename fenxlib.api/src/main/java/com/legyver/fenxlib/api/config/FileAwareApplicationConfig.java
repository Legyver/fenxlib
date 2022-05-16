package com.legyver.fenxlib.api.config;

import com.legyver.fenxlib.api.config.parts.ILastOpened;
import com.legyver.fenxlib.api.config.parts.IRecentlyModified;

/**
 * Base class for an application config that keeps track of recently modified and last opened files
 * Tracks the following
 *   1. Recently modified files
 *   2. Directory of last opened file
 * @param <M> IRecentlyModified data
 * @param <O> ILastOpened data
 */
public abstract class FileAwareApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> implements IApplicationConfig<M, O> {

}
