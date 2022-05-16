package com.legyver.fenxlib.api.config.parts;

import com.legyver.core.exception.CoreException;

import java.time.LocalDateTime;

/**
 * Config data pertaining to a recently-viewed file
 *
 */
public interface IRecentlyViewedFile {
	/**
	 * Get the name of the file
	 * @return the name of the file
	 * @throws CoreException if there is a problem reading the file name from the config file
	 */
	String getName() throws CoreException;

	/**
	 * Get the path to the file
	 * @return the path
	 * @throws CoreException if there is a problem reading the file path from the config file
	 */
	String getPath() throws CoreException;

	/**
	 * Get the LocalDateTime the file was last accessed
	 * @return the local date time of access
	 * @throws CoreException if there is a problem reading the access date-time from the config file
	 */
	LocalDateTime getLastAccessed() throws CoreException;

	/**
	 * Set the last accessed time
	 * @param now date-time to record
	 * @throws CoreException if there is a problem writing the access date-time to the config
	 */
	void setLastAccessed(LocalDateTime now) throws CoreException;
}
