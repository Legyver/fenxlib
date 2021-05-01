package com.legyver.fenxlib.core.impl.util;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyViewedFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class RecentlyViewedFileComparator implements Comparator<IRecentlyViewedFile> {
	private static final Logger logger = LogManager.getLogger(RecentlyViewedFileComparator.class);
	@Override
	public int compare(IRecentlyViewedFile o1, IRecentlyViewedFile o2) {
		try {
			return o1.getLastAccessed().compareTo(o2.getLastAccessed());
		} catch (CoreException e) {
			logger.error("Error comparing files based on last accessed date: " + e.getMessage(), e);
		}
		return 0;
	}

}
