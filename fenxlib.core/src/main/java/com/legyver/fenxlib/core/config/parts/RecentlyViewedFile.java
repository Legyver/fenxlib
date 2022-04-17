package com.legyver.fenxlib.core.config.parts;

import com.legyver.core.exception.CoreException;
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

	/**
	 * Set the name for the file
	 * @param name the (simple) name
	 * @throws CoreException if there is an error setting the value
	 */
	public void setName(String name) throws CoreException {
		this.name.set(name);
	}

	@Override
	public String getPath() throws CoreException {
		return path.get();
	}

	/**
	 * Set the path to the recently-viewed file
	 * @param path the (absolute) path to the file
	 * @throws CoreException if there is an error setting the value
	 */
	public void setPath(String path) throws CoreException {
		this.path.set(path);
	}

	/**
	 * Set the last-accessed date
	 * @param lastAccessed the last-accessed LocalDateTime
	 * @throws CoreException if there is an error setting the value
	 */
	public void setLastAccessed(LocalDateTime lastAccessed) throws CoreException {
		this.lastAccessed.set(lastAccessed);
	}

	/**
	 * Retrieve the last-accessed date-time
	 * @return the last-accessed date-time
	 * @throws CoreException if there is an error reading the value
	 */
	public LocalDateTime getLastAccessed() throws CoreException {
		return lastAccessed.get();
	}

	@Override
	public Map getRawMap() {
		return sourceMap;
	}

	/**
	 * Construct a RecentlyViewedFile descriptor from a file.
	 * - sets the recently viewed file name to the name of the file
	 * - sets the recently viewed file path to the absolute path of the file
	 * - sets the recently viewed file last accessed to now
	 * @param file the file
	 * @return the RecentlyViewedFile descriptor for the file
	 * @throws CoreException if there is an error setting any of the values
	 */
	public static RecentlyViewedFile parse(File file) throws CoreException {
		RecentlyViewedFile recentValue = new RecentlyViewedFile(new LinkedHashMap<>());
		recentValue.setPath(file.getAbsolutePath());
		recentValue.setName(file.getName());
		recentValue.setLastAccessed(LocalDateTime.now());
		return recentValue;
	}
}
