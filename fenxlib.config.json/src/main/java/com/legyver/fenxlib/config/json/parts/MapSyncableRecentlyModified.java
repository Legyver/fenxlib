package com.legyver.fenxlib.config.json.parts;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.parts.IRecentlyModified;
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
public class MapSyncableRecentlyModified implements IRecentlyModified<MapSyncableRecentlyViewedFile>, MapSyncable {
	private final Map sourceMap;
	private final MapBackedInteger limit;
	private final MapBackedCollection<List<MapSyncableRecentlyViewedFile>, MapSyncableRecentlyViewedFile> values;

	/**
	 * Construct a RecentlyModified POJO wrapping the raw value map.
	 * @param sourceMap map containing all the values
	 */
	public MapSyncableRecentlyModified(Map sourceMap) {
		this.sourceMap = sourceMap;
		this.limit = new MapBackedInteger(sourceMap, "limit", 5);
		this.values = new MapBackedEntityCollection(sourceMap, "values", m -> new MapSyncableRecentlyViewedFile(m));
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
	public List<MapSyncableRecentlyViewedFile> getValues() throws CoreException {
		return values.get();
	}

	@Override
	public void addValue(MapSyncableRecentlyViewedFile recentValue) throws CoreException {
		values.add(recentValue);
	}

	/**
	 * Update the appropriate recently-viewed file reference with the date-time of now if it exists
	 * @param path the path to the new file
	 * @throws CoreException if there is an error reading the list of recently-viewed files or setting the new date-time on the reference
	 */
	public void accept(String path) throws CoreException {
		Optional<MapSyncableRecentlyViewedFile> existingValue = CoreException.unwrap(
				() -> CoreException.wrap(
						() -> getValues().stream()
								.filter(v -> CoreException.wrap(
										() -> v.getPath().equalsIgnoreCase(path)))
								.findAny()));
		if (existingValue.isPresent()) {
			MapSyncableRecentlyViewedFile recentValue = existingValue.get();
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
