package com.legyver.fenxlib.core.api.config;

import com.legyver.fenxlib.core.api.config.parts.ILastOpened;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyModified;

/**
 * Tagging interface for application config for the fenxlib library
 * @param <M> the type of the recently modified config object.
 * @param <O> the type of the last opened config object
 */
public interface IApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> {
}
