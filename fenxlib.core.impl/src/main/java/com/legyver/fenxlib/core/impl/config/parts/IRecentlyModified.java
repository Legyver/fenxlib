package com.legyver.fenxlib.core.impl.config.parts;

import com.legyver.fenxlib.core.impl.config.IRecentlyViewedFile;

import java.util.List;

public interface IRecentlyModified<T extends IRecentlyViewedFile> {

	void addValue(T recentValue);

	int getLimit();

	List<T> getValues();

	void setLimit(int limit);

}
