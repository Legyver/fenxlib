package com.legyver.fenxlib.core.config.parts;

import com.legyver.core.exception.CoreException;

import java.util.List;

/**
 * Recently viewed data as stored in the config file
 * @param <T> the type of the recently viewed record
 */
public interface IRecentlyModified<T extends IRecentlyViewedFile> {

	/**
	 * Add a recently-viewed file to the configuration
	 * @param recentValue value to add
	 * @throws CoreException if there is a problem marshalling the value to JSON
	 */
	void addValue(T recentValue) throws CoreException;

	/**
	 * Retrieve the limit on how many files to show in recently-viewed menu
	 * @return the limit
	 * @throws CoreException if there is a problem marshalling the value from JSON
	 */
	int getLimit() throws CoreException;

	/**
	 * Retrieve the recently-viewed files data from the configuration
	 * @return the recently-viewed file records
	 * @throws CoreException if there is a problem reading the value from JSON
	 */
	List<T> getValues() throws CoreException;

	/**
	 * Set the limit on how many files to show in recently-viewed menu
	 * @param limit the max number of files to keep track of
	 * @throws CoreException if there is a problem setting the value on the backing config
	 */
	void setLimit(int limit) throws CoreException;

}
