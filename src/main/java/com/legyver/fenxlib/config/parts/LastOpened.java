package com.legyver.fenxlib.config.parts;

import com.legyver.util.mapqua.mapbacked.MapBackedString;
import com.legyver.util.mapqua.mapbacked.MapSyncable;
import java.util.Map;

public class LastOpened implements ILastOpened, MapSyncable {

	private final Map sourceMap;
	private final MapBackedString lastDirectory;

	public LastOpened(Map rawValues) {
		this.sourceMap = rawValues;
		this.lastDirectory = new MapBackedString(sourceMap, "lastDirectory");
	}

	@Override
	public String getLastDirectory() {
		return lastDirectory.get();
	}

	@Override
	public void setLastDirectory(String lastDirectory) {
		this.lastDirectory.set(lastDirectory);
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
