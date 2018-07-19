package com.legyver.fenxlib.config;

import com.legyver.fenxlib.config.parts.LastOpened;
import com.legyver.fenxlib.config.parts.RecentlyModified;
import com.legyver.fenxlib.util.ForwardCompatibleModel;

public class ApplicationConfig extends ForwardCompatibleModel {
	private RecentlyModified recentlyModified = new RecentlyModified();
	private LastOpened lastOpened = new LastOpened();

	public RecentlyModified getRecentlyModified() {
		return recentlyModified;
	}

	public void setRecentlyModified(RecentlyModified recentlyModified) {
		this.recentlyModified = recentlyModified;
	}

	public LastOpened getLastOpened() {
		return lastOpened;
	}

	public void setLastOpened(LastOpened lastOpened) {
		this.lastOpened = lastOpened;
	}
	
	
}
