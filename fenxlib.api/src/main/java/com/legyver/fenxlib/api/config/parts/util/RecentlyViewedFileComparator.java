package com.legyver.fenxlib.api.config.parts.util;

import com.legyver.fenxlib.api.config.parts.RecentFile;

import java.util.Comparator;

/**
 * Comparator for recently-viewed files.  Sorts them by most-recently accessed.
 */
public class RecentlyViewedFileComparator implements Comparator<RecentFile> {

	@Override
	public int compare(RecentFile o1, RecentFile o2) {
		return o1.getLastAccessed().compareTo(o2.getLastAccessed());
	}

}
