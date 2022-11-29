package com.legyver.fenxlib.widgets.filetree.config;

import com.legyver.fenxlib.api.config.section.FenxlibVersionedConfigSection;

/**
 * Configuration section for the FileTree widget
 */
public class FileTreeConfigSection implements FenxlibVersionedConfigSection {
    private WorkingFileSet workingFileSet = new WorkingFileSet();

    @Override
    public String getSectionName() {
        return "com.legyver.fenxlib.widgets.filetree";
    }

    /**
     * Get the FileSet of working files in the tree
     * @return the working files in the tree
     */
    public WorkingFileSet getWorkingFileSet() {
        return workingFileSet;
    }

    /**
     * Set the working fileset in the tree
     * @param workingFileSet the working file set displayed in the tree
     */
    public void setWorkingFileSet(WorkingFileSet workingFileSet) {
        this.workingFileSet = workingFileSet;
    }
}
