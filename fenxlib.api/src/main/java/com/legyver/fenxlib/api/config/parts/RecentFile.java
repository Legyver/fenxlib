package com.legyver.fenxlib.api.config.parts;

import java.io.File;
import java.time.LocalDateTime;

/**
 * Config data pertaining to a recently-viewed file
 *
 */
public class RecentFile {
	private String name;
	private String path;
	private LocalDateTime lastAccessed;

	/**
	 * Construct a recent file reference
	 */
	public RecentFile() {
		//no arg constructor for Jackson
	}

	/**
	 * Construct a recent file reference
	 * @param file a recent file
	 */
	public RecentFile(File file) {
		name = file.getName();
		path = file.getAbsolutePath();
		lastAccessed = LocalDateTime.now();
	}

	/**
	 * Get the name of the file
	 * @return the name of the file
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the simple file name.
	 * @param name the simple file name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the path to the file
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path to the file
	 * @param path the file path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Get the LocalDateTime the file was last accessed
	 * @return the local date time of access
	 */
	public LocalDateTime getLastAccessed() {
		return lastAccessed;
	}

	/**
	 * Set the last accessed time
	 * @param lastAccessed date-time to record
	 */
	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
}
