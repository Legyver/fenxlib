package com.legyver.fenxlib.config.json.parts;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.parts.ILastOpened;
import com.legyver.utils.mapqua.mapbacked.MapBackedString;
import com.legyver.utils.mapqua.mapbacked.MapSyncable;
import java.util.Map;

/**
 * Default implementation of ILastOpened
 * Uses legyver.utils.mapqua to represent the value as a POJO.
 */
public class MapSyncableLastOpened implements ILastOpened, MapSyncable {

	private final Map sourceMap;
	private final MapBackedString lastFile;

	/**
	 * Construct a LastOpened POJO wrapping the raw value map.
	 * @param rawValues map containing all the values
	 */
	public MapSyncableLastOpened(Map rawValues) {
		this.sourceMap = rawValues;
		this.lastFile = new MapBackedString(sourceMap, "lastFile");
	}

	@Override
	public String getLastFile() throws CoreException {
		return lastFile.get();
	}

	@Override
	public void setLastFile(String lastFile) throws CoreException {
		this.lastFile.set(lastFile);
	}

	@Override
	public void sync() {
		//noop
	}

	@Override
	public Map getRawMap() {
		return sourceMap;
	}

}
