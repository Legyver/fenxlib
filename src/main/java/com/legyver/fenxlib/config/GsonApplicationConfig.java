package com.legyver.fenxlib.config;

import com.legyver.fenxlib.config.parts.LastOpened;
import com.legyver.fenxlib.config.parts.RecentlyModified;
import com.legyver.util.mapqua.MapQuery;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class GsonApplicationConfig extends ApplicationConfig<RecentlyModified, LastOpened> implements RawMapAware {
	private final Map rawMap;
	private RecentlyModified recentlyModified;
	private LastOpened lastOpened;
	
	public GsonApplicationConfig(Map map) {
		this.rawMap = map;
	}

	@Override
	public RecentlyModified getRecentlyModified() {
		if (recentlyModified == null) {
			Optional<Object> rmOption = new MapQuery.Query().object("recentlyModified").execute(rawMap);
			if (rmOption.isPresent()) {
				recentlyModified = new RecentlyModified((Map) rmOption.get());
			} else {
				recentlyModified = new RecentlyModified(new LinkedHashMap());
			}
		}
		
		return recentlyModified;
	}

	@Override
	public void setRecentlyModified(RecentlyModified recentlyModified) {
		this.recentlyModified = recentlyModified;
		new MapQuery.Query().merge("recentlyModified", recentlyModified).execute(rawMap);
	}

	@Override
	public LastOpened getLastOpened() {
		if (lastOpened == null) {
			Optional<Object> rmOption = new MapQuery.Query().object("lastOpened").execute(rawMap);
			if (rmOption.isPresent()) {
				lastOpened = new LastOpened((Map) rmOption.get());
			}
		}
		
		return lastOpened;
	}

	@Override
	public void setLastOpened(LastOpened lastOpened) {
//		this.lastOpened.set(lastOpened);
	}

	@Override
	public Map getRawMap() {
		return rawMap;
	}

}
