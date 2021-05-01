package com.legyver.fenxlib.core.api.config.parts;

import com.legyver.core.exception.CoreException;

/**
 * Last opened file information
 */
public interface ILastOpened {

	/**
	 * Get the last opened file name
	 * @return the last file name
	 * @throws CoreException if there is an error loading it from config
	 */
	String getLastFile() throws CoreException;

	/**
	 * Set the last opened file name
	 * @param lastFile the last file
	 * @throws CoreException if there is an error saving it to config
	 */
	void setLastFile(String lastFile) throws CoreException;

}
