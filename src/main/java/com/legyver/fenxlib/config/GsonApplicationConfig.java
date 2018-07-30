package com.legyver.fenxlib.config;

import com.legyver.fenxlib.config.parts.LastOpened;
import com.legyver.fenxlib.config.parts.RecentlyModified;
import com.legyver.util.mapqua.mapbacked.MapBackedEntity;
import com.legyver.util.mapqua.mapbacked.RawMapAware;
import java.util.Map;

public class GsonApplicationConfig extends ApplicationConfig<RecentlyModified, LastOpened> implements RawMapAware {
	private final Map rawMap;
	private final MapBackedEntity<RecentlyModified> recentlyModified;
	private final MapBackedEntity<LastOpened> lastOpened;

	public GsonApplicationConfig(Map map) {
		this.rawMap = map;
		this.recentlyModified = new MapBackedEntity<>(map, "recentlyModified", (m) -> new RecentlyModified(m));
		this.lastOpened = new MapBackedEntity<>(map, "lastOpened", (m) -> new LastOpened(m));
	}

	@Override
	public RecentlyModified getRecentlyModified() {
		return recentlyModified.get();
	}

	@Override
	public LastOpened getLastOpened() {
		return lastOpened.get();
	}

	@Override
	public Map getRawMap() {
		return rawMap;
	}

	@Override
	public void sync() {
		getRecentlyModified().sync();
		getLastOpened().sync();//technically not needed, since it doesn't contain a collection
	}

}
