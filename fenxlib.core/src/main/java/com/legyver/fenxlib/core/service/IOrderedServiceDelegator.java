package com.legyver.fenxlib.core.service;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/**
 * OrderedService delegator to ensure order is respected
 * @param <T> the type or the ordered service
 */
public interface IOrderedServiceDelegator<T extends OrderedService> {
    /**
     * Get the most preferred (lowest {@link OrderedService#order()}) OrderedService
     * @return the OrderedService with the lowest order, or null if none exists
     */
    T getDelegate();

    /**
     * Iterator iterate over services in most to least preferred
     * @return iterator
     */
    Iterator<T> iterator();

    /**
     * Split the services into a map based on the splitting function
     * @param keyMapper the splitting function
     * @return the resulting map
     */
    Map<String, IOrderedServiceDelegator<T>> split(Function<T, String> keyMapper);

    /**
     * Check if there is at least one service
     * @return true if at least one service exists
     */
    boolean isEmpty();
}
