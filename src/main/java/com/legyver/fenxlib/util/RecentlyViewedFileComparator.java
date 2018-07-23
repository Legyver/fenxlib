package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.parts.RecentlyViewedFile;
import java.util.Comparator;

public class RecentlyViewedFileComparator implements Comparator<RecentlyViewedFile> {

	@Override
	public int compare(RecentlyViewedFile o1, RecentlyViewedFile o2) {
		return o1.getLastAccessed().compareTo(o2.getLastAccessed());
	}

}
