package com.legyver.fenxlib.core.config;

import java.time.LocalDateTime;

public interface IRecentlyViewedFile {
	String getName();
	String getPath();
	LocalDateTime getLastAccessed();
}
