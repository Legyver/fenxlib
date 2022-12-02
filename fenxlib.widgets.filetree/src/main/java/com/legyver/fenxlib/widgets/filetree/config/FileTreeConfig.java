package com.legyver.fenxlib.widgets.filetree.config;

import com.legyver.fenxlib.api.config.section.ConfigPersisted;
import com.legyver.fenxlib.core.config.CoreApplicationConfig;

/**
 * Config that handles backing up the file tree working-fileset, so you can pick up where you left off without having
 * to re-add all the files/folders into the file tree.
 */
public class FileTreeConfig extends CoreApplicationConfig implements FileTreeConfigAware {

    @ConfigPersisted
    private FileTreeConfigSection fileTreeConfig = new FileTreeConfigSection();

    @Override
    public FileTreeConfigSection getFileTreeConfig() {
        return fileTreeConfig;
    }

    @Override
    public void setFileTreeConfig(FileTreeConfigSection fileTreeConfig) {
        this.fileTreeConfig = fileTreeConfig;
    }
}
