package com.legyver.fenxlib.config.parts;

import java.util.List;

public interface IRecentlyModified {

	void addValue(RecentlyViewedFile recentValue);

	int getLimit();

	List<RecentlyViewedFile> getValues();

	void setLimit(int limit);

}
