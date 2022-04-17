package com.legyver.fenxlib.core.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.config.parts.LastOpened;
import com.legyver.fenxlib.core.config.parts.RecentlyModified;
import com.legyver.utils.mapqua.mapbacked.MapBackedEntity;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;
import java.util.Map;

/**
 * Application config that is stored in JSON.
 * Note: this is based on legyver.utils.mapqua library to be forward and backward compatible
 */
public class JsonApplicationConfig extends ApplicationConfig<RecentlyModified, LastOpened> implements RawMapAware {
	private final Map rawMap;
	private final MapBackedEntity<RecentlyModified> recentlyModified;
	private final MapBackedEntity<LastOpened> lastOpened;

	/**
	 * Construct an Application Config the will marshall the config to-from JSON
	 * @param map the map of values to save
	 */
	public JsonApplicationConfig(Map map) {
		this.rawMap = map;
		this.recentlyModified = new MapBackedEntity<>(map, "recentlyModified", (m) -> new RecentlyModified(m));
		this.lastOpened = new MapBackedEntity<>(map, "lastOpened", (m) -> new LastOpened(m));
	}

	@Override
	public RecentlyModified getRecentlyModified() throws CoreException {
		return recentlyModified.get();
	}

	@Override
	public LastOpened getLastOpened() throws CoreException {
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
