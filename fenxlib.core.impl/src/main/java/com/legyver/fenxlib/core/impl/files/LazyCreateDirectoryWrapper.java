package com.legyver.fenxlib.core.impl.files;

import java.io.File;

/**
 * Creates a directory if it does not exist when it is asked to load a file from the directory
 */
public class LazyCreateDirectoryWrapper {
	private final File directory;

	/**
	 * Wrap a directory in a lazy create wrapper so we don't have to worry about whether or not it exists
	 * @param directory the directory
	 */
	public LazyCreateDirectoryWrapper(File directory) {
		this.directory = directory;
	}

	/**
	 * Get the directory.
	 * If it doesn't already exist, it will be created.
	 * @return the directory.
	 */
	public File getDirectory() {
		ensureDirectoryExists(directory);
		return directory;
	}

	/**
	 * Load a file from the directory.
	 * If the directory doesn't already exist, it will be created.
	 * @param filename the file to load
	 * @return the file reference
	 */
	public File loadFileFromDir(String filename) {
		ensureDirectoryExists(directory);
		return new File(directory.getAbsolutePath() + File.separator + filename);
	}

	/**
	 * Get the full filename formed by directory + File.separator + filename
	 * @param filename the filename
	 * @return the concatenation of the directory and filename with the appropriate separator
	 */
	public String getFullFilename(String filename) {
		return directory.getAbsolutePath() + File.separator + filename;
	}

	private void ensureDirectoryExists(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * Get the absolute path of the directory
	 * @return the absolute path
	 */
	public String getAbsolutePath() {
		return directory.getAbsolutePath();
	}
}
