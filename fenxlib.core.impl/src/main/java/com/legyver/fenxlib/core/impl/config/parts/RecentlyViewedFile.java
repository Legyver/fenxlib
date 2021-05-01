package com.legyver.fenxlib.core.impl.config.parts;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyViewedFile;
import com.legyver.utils.mapqua.mapbacked.MapBackedLocalDateTime;
import com.legyver.utils.mapqua.mapbacked.MapBackedString;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;
import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default implementation of IRecentlyViewedFile
 * Uses legyver.utils.mapqua to represent the value as a POJO.
 */
public class RecentlyViewedFile implements RawMapAware, IRecentlyViewedFile {

	private final Map sourceMap;
	private final MapBackedString name;
	private final MapBackedString path;
	private final MapBackedLocalDateTime lastAccessed;

	/**
	 * Construct a RecentlyViewedFile POJO wrapping the raw value map.
	 * @param sourceMap map containing all the values
	 */
	public RecentlyViewedFile(Map sourceMap) {
		this.sourceMap = sourceMap;
		this.name = new MapBackedString(sourceMap, "name");
		this.path = new MapBackedString(sourceMap, "path");
		this.lastAccessed = new MapBackedLocalDateTime(sourceMap, "lastAccessed", LocalDateTime.MIN);//if unknown, make a long time ago
	}

	@Override
	public String getName() throws CoreException {
		return name.get();
	}

	public void setName(String name) throws CoreException {
		this.name.set(name);
	}

	@Override
	public String getPath() throws CoreException {
		return path.get();
	}

	public void setPath(String path) throws CoreException {
		this.path.set(path);
	}

	public void setLastAccessed(LocalDateTime lastAccessed) throws CoreException {
		this.lastAccessed.set(lastAccessed);
	}

	public LocalDateTime getLastAccessed() throws CoreException {
		return lastAccessed.get();
	}

	@Override
	public Map getRawMap() {
		return sourceMap;
	}

	public static RecentlyViewedFile parse(File file) throws CoreException {
		RecentlyViewedFile recentValue = new RecentlyViewedFile(new LinkedHashMap<>());
		recentValue.setPath(file.getAbsolutePath());
		recentValue.setName(file.getName());
		recentValue.setLastAccessed(LocalDateTime.now());
		return recentValue;
	}
}
