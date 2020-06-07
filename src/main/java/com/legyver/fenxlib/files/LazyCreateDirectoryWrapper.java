package com.legyver.fenxlib.files;

import java.io.File;

public class LazyCreateDirectoryWrapper {
	private final File directory;

	public LazyCreateDirectoryWrapper(File directory) {
		this.directory = directory;
	}

	public File getDirectory() {
		ensureDirectoryExists(directory);
		return directory;
	}

	public File loadFileFromDir(String filename) {
		ensureDirectoryExists(directory);
		return new File(directory.getAbsolutePath() + File.separator + filename);
	}

	private void ensureDirectoryExists(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public String getAbsolutePath() {
		return directory.getAbsolutePath();
	}
}
