package com.legyver.fenxlib.api.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.parts.ILastOpened;
import com.legyver.fenxlib.api.config.parts.IRecentlyModified;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;

/**
 * Tagging interface for application config for the fenxlib library
 * @param <M> the type of the recently modified config object.
 * @param <O> the type of the last opened config object
 */
public interface IApplicationConfig<M extends IRecentlyModified, O extends ILastOpened> {
    /**
     * Retrieve the "recently-modified" section of the config
     * @return the "recently-modified" section of the config
     * @throws CoreException if there is a problem parsing the config
     */
    M getRecentlyModified() throws CoreException;

    /**
     * Retrieve the "last-opened" section of the config
     * @return the "last-opened" section of the config
     * @throws CoreException if there is a problem parsing the config
     */
    O getLastOpened() throws CoreException;
}
