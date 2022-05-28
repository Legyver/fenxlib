package com.legyver.fenxlib.tests.base.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.FileAwareApplicationConfig;
import com.legyver.fenxlib.api.config.parts.ILastOpened;
import com.legyver.fenxlib.api.config.parts.IRecentlyModified;
import com.legyver.fenxlib.api.config.parts.IRecentlyViewedFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Test config for json-based configs
 */
public class TestConfig extends FileAwareApplicationConfig<IRecentlyModified, ILastOpened> {
	private IRecentlyModified recentlyModified = new InMemoryRecentlyModified();
	private ILastOpened lastOpened = new InMemoryLastOpened();

	@Override
	public IRecentlyModified getRecentlyModified() {
		return recentlyModified;
	}

	@Override
	public ILastOpened getLastOpened() {
		return lastOpened;
	}

	private static class InMemoryRecentlyModified implements IRecentlyModified {
		private int limit = 0;
		private final List<IRecentlyViewedFile> recentlyViewedFiles = new ArrayList<>();
		@Override
		public void addValue(IRecentlyViewedFile recentValue) throws CoreException {
			recentlyViewedFiles.add(recentValue);
		}

		@Override
		public int getLimit() throws CoreException {
			return limit;
		}

		@Override
		public List getValues() throws CoreException {
			return recentlyViewedFiles;
		}

		@Override
		public void setLimit(int limit) throws CoreException {
			this.limit = limit;
		}
	}

	private static class InMemoryLastOpened implements ILastOpened {
		private String lastFile;

		@Override
		public String getLastFile() throws CoreException {
			return lastFile;
		}

		@Override
		public void setLastFile(String lastFile) throws CoreException {
			this.lastFile = lastFile;
		}
	}
}
