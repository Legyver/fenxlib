package com.legyver.fenxlib.core.config.parts;

import com.legyver.util.mapqua.mapbacked.MapBackedLocalDateTime;
import com.legyver.util.mapqua.mapbacked.MapBackedString;
import com.legyver.util.mapqua.mapbacked.RawMapAware;
import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecentlyViewedFile implements RawMapAware {

	private final Map sourceMap;
	private final MapBackedString name;
	private final MapBackedString path;
	private final MapBackedLocalDateTime lastAccessed;

	public RecentlyViewedFile(Map sourceMap) {
		this.sourceMap = sourceMap;
		this.name = new MapBackedString(sourceMap, "name");
		this.path = new MapBackedString(sourceMap, "path");
		this.lastAccessed = new MapBackedLocalDateTime(sourceMap, "lastAccessed", LocalDateTime.MIN);//if unknown, make a long time ago
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getPath() {
		return path.get();
	}

	public void setPath(String path) {
		this.path.set(path);
	}

	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed.set(lastAccessed);
	}

	public LocalDateTime getLastAccessed() {
		return lastAccessed.get();
	}

		@Override
	public Map getRawMap() {
		return sourceMap;
	}

	public static RecentlyViewedFile parse(File file) {
		RecentlyViewedFile recentValue = new RecentlyViewedFile(new LinkedHashMap<>());
		recentValue.setPath(file.getAbsolutePath());
		recentValue.setName(file.getName());
		recentValue.setLastAccessed(LocalDateTime.now());
		return recentValue;
	}
}
