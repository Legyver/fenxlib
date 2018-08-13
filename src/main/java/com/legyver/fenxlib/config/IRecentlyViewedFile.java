package com.legyver.fenxlib.config;

import java.time.LocalDateTime;

public interface IRecentlyViewedFile {
	String getName();
	String getPath();
	LocalDateTime getLastAccessed();
}
