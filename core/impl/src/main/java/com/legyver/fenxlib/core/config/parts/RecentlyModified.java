package com.legyver.fenxlib.core.config.parts;

import com.legyver.util.mapqua.mapbacked.MapBackedCollection;
import com.legyver.util.mapqua.mapbacked.MapBackedEntityCollection;
import com.legyver.util.mapqua.mapbacked.MapBackedInteger;
import com.legyver.util.mapqua.mapbacked.MapSyncable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecentlyModified implements IRecentlyModified, MapSyncable {
	private final Map sourceMap;
	private final MapBackedInteger limit;
	private final MapBackedCollection<List<RecentlyViewedFile>, RecentlyViewedFile> values;

	public RecentlyModified(Map sourceMap) {
		this.sourceMap = sourceMap;
		this.limit = new MapBackedInteger(sourceMap, "limit", 5);
		this.values = new MapBackedEntityCollection(sourceMap, "values", m -> new RecentlyViewedFile(m));
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

	@Override
	public void sync() {
		values.sync();
	}

	@Override
	public Map getRawMap() {
		return sourceMap;
	}

}
