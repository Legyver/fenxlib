package com.legyver.fenxlib.config.parts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecentlyModified  {	
	/**
	 * Number of recent files to track
	 */
	private int limit = 5;
	/**
	 * Tracked recent files
	 */
	private final List<RecentlyViewedFile> values = new ArrayList<>();
	

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<RecentlyViewedFile> getValues() {
		return values;
	}

	public void addValue(RecentlyViewedFile recentValue) {
		values.add(recentValue);
	}

	public void accept(String newPath) {
		Optional<RecentlyViewedFile> existingValue = values.stream().filter(v -> v.getPath().equalsIgnoreCase(newPath)).findAny();
		if (existingValue.isPresent()) {
			RecentlyViewedFile recentValue = existingValue.get();
			recentValue.setLastAccessed(LocalDateTime.now());
		}
	}

}
