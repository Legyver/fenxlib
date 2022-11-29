package com.legyver.fenxlib.widgets.filetree.config;

import com.legyver.fenxlib.api.config.parts.RecentFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Files/Directories currently imported to the FileTree
 */
public class WorkingFileSet {
    private List<RecentFile> values = new ArrayList<>();

    /**
     * Get list of files imported to the FileTree
     * @return the values
     */
    public List<RecentFile> getValues() {
        return values;
    }

    /**
     * Add a file to the list of files in the FileTree
     * @param recentlyViewedFile the file added to the FileTree
     */
    public void addValue(RecentFile recentlyViewedFile) {
        values.add(recentlyViewedFile);
    }

}
