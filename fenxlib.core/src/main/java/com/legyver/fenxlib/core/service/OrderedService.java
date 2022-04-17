package com.legyver.fenxlib.core.service;

/**
 * Comparable interface for services to allow them to be run in order
 * @param <T> the type of the service
 */
public interface OrderedService<T extends OrderedService> extends Comparable<T> {
	default int compareTo(T o) {
		return this.order() - o.order();
	}

	/**
	 * Order in which the ServiceImpl is to be run
	 * Lowest number ran first
	 * @return the order
	 */
	int order();
}
