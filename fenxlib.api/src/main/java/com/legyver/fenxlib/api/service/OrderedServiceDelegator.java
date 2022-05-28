package com.legyver.fenxlib.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Ensure the highest preference (lowest order) service is used
 * @param <T> the type of the service
 */
@SuppressWarnings("unchecked")
public class OrderedServiceDelegator<T extends OrderedService> implements IOrderedServiceDelegator {
    private static final Logger logger = LogManager.getLogger(OrderedServiceDelegator.class);

    private final ServiceLoader<T> serviceLoader;
    private final List<T> services = new ArrayList<>();
    private final Predicate<T> delegatePredicate;
    private T delegate;
    private boolean initialized;

    /**
     * Construct an delegator to ensure that services are accessed in order of preference
     * @param serviceLoader service loader
     * @param delegatePredicate predicate to select the service as a delegate
     */
    public OrderedServiceDelegator(ServiceLoader<T> serviceLoader, Predicate<T> delegatePredicate) {
        this.serviceLoader = serviceLoader;
        this.delegatePredicate = delegatePredicate;
    }
    /**
     * Construct an delegator to ensure that services are accessed in order of preference
     * @param serviceLoader service loader
     */
    public OrderedServiceDelegator(ServiceLoader<T> serviceLoader) {
        this(serviceLoader, new FirstPredicate<T>());
    }

    private synchronized void initialize() {
        if (!initialized) {
            for (Iterator<T> it = serviceLoader.iterator(); it.hasNext(); ) {
                T service = it.next();
                services.add(service);
            }
            Collections.sort(services);
            if (!services.isEmpty()) {
                List<T> matchedDelegates = new ArrayList<>();
                for (Iterator<T> it = services.iterator(); it.hasNext(); ) {
                    T service = it.next();
                    if (delegatePredicate.test(service)) {
                        logger.debug("Service {} matched predicate.", service);
                        matchedDelegates.add(service);
                    }
                }

                T service;
                if (matchedDelegates.isEmpty()) {
                    service = services.iterator().next();
                    logger.warn("No service matched predicate {}.  Using first service: {}", delegatePredicate, service);
                } else {
                    service = matchedDelegates.iterator().next();
                    logger.debug("Using highest preference service of {} matched services: {}", matchedDelegates.size(), service);
                }
                this.delegate = service;
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
    public int size() {
        return services.size();
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
            return OrderedServiceDelegator.split(services, keyMapper);
        }

        @Override
        public boolean isEmpty() {
            return services.isEmpty();
        }

        @Override
        public int size() {
            return services.size();
        }
    }

    private static class FirstPredicate<T> implements Predicate<T> {
        private boolean first = true;

        @Override
        public boolean test(T t) {
            boolean result = first;
            first = false;
            return result;
        }
    }
}