package com.legyver.fenxlib.core.impl.config.parts;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyModified;
import com.legyver.utils.mapqua.mapbacked.MapBackedCollection;
import com.legyver.utils.mapqua.mapbacked.MapBackedEntityCollection;
import com.legyver.utils.mapqua.mapbacked.MapBackedInteger;
import com.legyver.utils.mapqua.mapbacked.MapSyncable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Default implementation of IRecentlyModified
 * Uses legyver.utils.mapqua to represent the value as a POJO.
 */
public class RecentlyModified implements IRecentlyModified<RecentlyViewedFile>, MapSyncable {
	private final Map sourceMap;
	private final MapBackedInteger limit;
	private final MapBackedCollection<List<RecentlyViewedFile>, RecentlyViewedFile> values;

	/**
	 * Construct a RecentlyModified POJO wrapping the raw value map.
	 * @param sourceMap map containing all the values
	 */
	public RecentlyModified(Map sourceMap) {
		this.sourceMap = sourceMap;
		this.limit = new MapBackedInteger(sourceMap, "limit", 5);
		this.values = new MapBackedEntityCollection(sourceMap, "values", m -> new RecentlyViewedFile(m));
	}

	@Override
	public int getLimit() throws CoreException {
		return limit.get();
	}

	@Override
	public void setLimit(int limit) throws CoreException {
		this.limit.set(limit);
	}

	@Override
	public List<RecentlyViewedFile> getValues() throws CoreException {
		return values.get();
	}

	@Override
	public void addValue(RecentlyViewedFile recentValue) throws CoreException {
		values.add(recentValue);
	}

	public void accept(String newPath) throws CoreException {
		Optional<RecentlyViewedFile> existingValue = CoreException.unwrap(
				() -> CoreException.wrap(
						() -> getValues().stream()
								.filter(v -> CoreException.wrap(
										() -> v.getPath().equalsIgnoreCase(newPath)))
								.findAny()));
		if (existingValue.isPresent()) {
			RecentlyViewedFile recentValue = existingValue.get();
			recentValue.setLastAccessed(LocalDateTime.now());
		}
	}

	@Override
	public void sync() throws CoreException {
		values.sync();
	}

	@Override
	public Map getRawMap() {
		return sourceMap;
	}

}
