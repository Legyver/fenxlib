package com.legyver.fenxlib.core.impl.config;

import java.time.LocalDateTime;

public interface IRecentlyViewedFile {
	String getName();
	String getPath();
	LocalDateTime getLastAccessed();
	void setLastAccessed(LocalDateTime now);
}
