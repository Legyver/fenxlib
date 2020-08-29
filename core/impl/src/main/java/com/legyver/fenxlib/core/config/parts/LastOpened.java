package com.legyver.fenxlib.core.config.parts;

import com.legyver.util.mapqua.mapbacked.MapBackedString;
import com.legyver.util.mapqua.mapbacked.MapSyncable;
import java.util.Map;

public class LastOpened implements ILastOpened, MapSyncable {

	private final Map sourceMap;
	private final MapBackedString lastFile;

	public LastOpened(Map rawValues) {
		this.sourceMap = rawValues;
		this.lastFile = new MapBackedString(sourceMap, "lastFile");
	}

	@Override
	public String getLastFile() {
		return lastFile.get();
	}

	@Override
	public void setLastFile(String lastFile) {
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
