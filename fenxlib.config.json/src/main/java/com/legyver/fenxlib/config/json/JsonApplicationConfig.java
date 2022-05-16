package com.legyver.fenxlib.config.json;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.FileAwareApplicationConfig;
import com.legyver.fenxlib.config.json.parts.MapSyncableLastOpened;
import com.legyver.fenxlib.config.json.parts.MapSyncableRecentlyModified;
import com.legyver.utils.mapqua.mapbacked.MapBackedEntity;
import com.legyver.utils.mapqua.mapbacked.MapSyncable;

import java.util.Map;

/**
 * Application config that is stored in JSON.
 * Note: this is based on legyver.utils.mapqua library to be forward and backward compatible
 */
public class JsonApplicationConfig extends FileAwareApplicationConfig<MapSyncableRecentlyModified, MapSyncableLastOpened> implements MapSyncable {
	private final Map rawMap;
	private final MapBackedEntity<MapSyncableRecentlyModified> recentlyModified;
	private final MapBackedEntity<MapSyncableLastOpened> lastOpened;

	/**
	 * Construct an Application Config the will marshall the config to-from JSON
	 * @param map the map of values to save
	 */
	public JsonApplicationConfig(Map map) {
		this.rawMap = map;
		this.recentlyModified = new MapBackedEntity<>(map, "recentlyModified", (m) -> new MapSyncableRecentlyModified(m));
		this.lastOpened = new MapBackedEntity<>(map, "lastOpened", (m) -> new MapSyncableLastOpened(m));
	}

	@Override
	public MapSyncableRecentlyModified getRecentlyModified() throws CoreException {
		return recentlyModified.get();
	}

	@Override
	public MapSyncableLastOpened getLastOpened() throws CoreException {
		return lastOpened.get();
	}

	@Override
	public Map getRawMap() {
		return rawMap;
	}

	@Override
	public void sync() throws CoreException {
		getRecentlyModified().sync();
		getLastOpened().sync();//technically not needed, since it doesn't contain a collection
	}

}
