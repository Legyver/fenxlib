package com.legyver.fenxlib.api.config.adapter;

import com.legyver.core.exception.CoreException;

/**
 * Adapter to adapt portions of a config file
 * @param <A> the name of the adapter
 * @param <T> the type of the adapter
 */
public interface IConfigAdapter<A, T> {
    /**
     * Adapt the arg
     * @param arg the argument to adapt
     * @return the adapted object
     * @throws CoreException if there is an error
     */
    T adapt(A arg) throws CoreException;
}
