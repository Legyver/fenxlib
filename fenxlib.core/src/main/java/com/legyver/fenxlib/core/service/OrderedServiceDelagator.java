package com.legyver.fenxlib.core.service;

import java.util.*;
import java.util.List;
import java.util.function.Function;

/**
 * Ensure the highest preference (lowest order) service is used
 * @param <T> the type of the service
 */
@SuppressWarnings("unchecked")
public class OrderedServiceDelagator<T extends OrderedService> implements IOrderedServiceDelegator {
    private final ServiceLoader<T> serviceLoader;
    private final List<T> services = new ArrayList<>();
    private T delegate;
    private boolean initialized;

    /**
     * Construct an delegator to ensure that services are accessed in order of preference
     * @param serviceLoader service loader
     */
    public OrderedServiceDelagator(ServiceLoader<T> serviceLoader) {
        this.serviceLoader = serviceLoader;
    }

    private synchronized void initialize() {
        if (!initialized) {
            for (Iterator<T> it = serviceLoader.iterator(); it.hasNext(); ) {
                T service = it.next();
                services.add(service);
            }
            Collections.sort(services);
            if (!services.isEmpty()) {
                delegate = services.iterator().next();
            }
        }
        initialized = true;
    }

    /**
     * Get the preferred service
     * @return the delegate or null if there are no appropriate services
     */
    @Override
    public T getDelegate() {
        if (!initialized) {
            initialize();
        }
        return delegate;
    }

    /**
     * Highest to lowest-preference service iterator
     * @return the service iterator
     */
    @Override
    public Iterator<T> iterator() {
        if (!initialized) {
            initialize();
        }
        return services.iterator();
    }

    @Override
    public boolean isEmpty() {
        return services.isEmpty();
    }

    @Override
    public Map<String, IOrderedServiceDelegator<T>> split(Function keyMapper) {
        if (!initialized) {
            initialize();
        }
        return split(services, keyMapper);
    }

    private static <U extends OrderedService> Map<String, IOrderedServiceDelegator<U>> split(List<U> services, Function<U, String> keyMapper) {
        Map<String, IOrderedServiceDelegator<U>> map = new HashMap<>();
        for (U service : services) {
            String key = keyMapper.apply(service);
            SplitOrderedServiceDelegator<U> splitOrderedServiceDelegator = (SplitOrderedServiceDelegator<U>) map.computeIfAbsent(key, x -> new SplitOrderedServiceDelegator<>());
            splitOrderedServiceDelegator.services.add(service);
            if (splitOrderedServiceDelegator.delegate == null) {//these have already been sorted
                splitOrderedServiceDelegator.delegate = service;
            }
        }
        return map;
    }

    private static class SplitOrderedServiceDelegator<T extends OrderedService> implements IOrderedServiceDelegator<T> {
        private final List<T> services = new ArrayList<>();
        private T delegate;

        @Override
        public T getDelegate() {
            return delegate;
        }

        @Override
        public Iterator<T> iterator() {
            return services.iterator();
        }

        @Override
        public Map<String, IOrderedServiceDelegator<T>> split(Function<T, String> keyMapper) {
            return OrderedServiceDelagator.split(services, keyMapper);
        }

        @Override
        public boolean isEmpty() {
            return services.isEmpty();
        }
    }
}