package com.legyver.fenxlib.api.config.parts;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds data about the last {@link #limit} files opened.
 */
public class RecentFiles {
    private List<RecentFile> values = new ArrayList<>();
    private Integer limit = 10;

    /**
     * Add a recentFile reference
     * @param recentFile recent file to add
     */
    public void addRecentFile(RecentFile recentFile) {
        values.add(recentFile);
    }

    /**
     * Get the limit
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Get the list of files
     * @return the list of files
     */
    public List<RecentFile> getValues() {
        return values;
    }

    /**
     * Set the limit for files to display
     * @param limit the limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }


}
