package com.legyver.fenxlib.config.parts;

import com.legyver.util.mapqua.mapbacked.MapBackedCollection;
import com.legyver.util.mapqua.mapbacked.MapBackedInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecentlyModified implements IRecentlyModified  {	
	private final MapBackedInteger limit;
	private final MapBackedCollection<List<RecentlyViewedFile>, RecentlyViewedFile> values;
	
	public RecentlyModified(Map sourceMap) {
		this.limit = new MapBackedInteger(sourceMap, "limit", 5);
		this.values = new MapBackedCollection(sourceMap, "values", m -> new RecentlyViewedFile(m));
	}

	@Override
	public int getLimit() {
		return limit.get();
	}

	@Override
	public void setLimit(int limit) {
		this.limit.set(limit);
	}

	@Override
	public List<RecentlyViewedFile> getValues() {
		return values.get();
	}

	@Override
	public void addValue(RecentlyViewedFile recentValue) {
		values.add(recentValue);
	}

	public void accept(String newPath) {
		Optional<RecentlyViewedFile> existingValue = getValues().stream()
				.filter(v -> v.getPath().equalsIgnoreCase(newPath)).findAny();
		if (existingValue.isPresent()) {
			RecentlyViewedFile recentValue = existingValue.get();
			recentValue.setLastAccessed(LocalDateTime.now());
		}
	}

}
