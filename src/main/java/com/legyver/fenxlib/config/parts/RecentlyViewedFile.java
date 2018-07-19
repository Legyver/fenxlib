package com.legyver.fenxlib.config.parts;

import java.io.File;
import java.time.LocalDateTime;

public class RecentlyViewedFile {

	private LocalDateTime lastAccessed; 
	private String name;
	private String path;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	
	public LocalDateTime getLastAccessed() {
		return lastAccessed;
	}
	
	public static RecentlyViewedFile parse(String newValue) {
		RecentlyViewedFile recentValue = new RecentlyViewedFile();
		recentValue.setPath(newValue);
		File file = new File(newValue);
		recentValue.setName(file.getName());
		recentValue.setLastAccessed(LocalDateTime.now());
		return recentValue;
	}

}
