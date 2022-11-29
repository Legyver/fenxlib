package com.legyver.fenxlib.core.config;

import com.legyver.fenxlib.api.config.parts.LastOpened;
import com.legyver.fenxlib.api.config.parts.RecentFiles;
import com.legyver.fenxlib.api.config.section.FenxlibVersionedConfigSection;

/**
 * Configuration for the com.legyver.fenxlib.core module
 */
public class CoreConfigSection implements FenxlibVersionedConfigSection {
    private RecentFiles recentFiles = new RecentFiles();
    private LastOpened lastOpened = new LastOpened();

    @Override
    public String getSectionName() {
        return "com.legyver.fenxlib.core";
    }

    /**
     * Get recently modified files
     * @return the recently modified files section
     */
    public RecentFiles getRecentFiles() {
        return recentFiles;
    }

    /**
     * Set the recently modified files
     * @param recentFiles the recently modified files
     */
    public void setRecentFiles(RecentFiles recentFiles) {
        this.recentFiles = recentFiles;
    }

    /**
     * Get the information about the last opened file
     * @return the last-opened file information
     */
    public LastOpened getLastOpened() {
        return lastOpened;
    }

    /**
     * Set the information about the last opened file
     * @param lastOpened the last opened file information to save
     */
    public void setLastOpened(LastOpened lastOpened) {
        this.lastOpened = lastOpened;
    }
}
