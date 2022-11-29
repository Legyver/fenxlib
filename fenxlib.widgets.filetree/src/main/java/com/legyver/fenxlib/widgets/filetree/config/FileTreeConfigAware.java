package com.legyver.fenxlib.widgets.filetree.config;

import com.legyver.fenxlib.api.config.IApplicationConfig;

/**
 * FileTreeConfig.  implement this in your ApplicationConfig to remember last files.
 * Don't forget to annotate the field with {@link com.legyver.fenxlib.api.config.section.ConfigPersisted}
 */
public interface FileTreeConfigAware extends IApplicationConfig {
    /**
     * Get the file tree configuration
     * @return the file tree configuration section
     */
    FileTreeConfigSection getFileTreeConfig();
}
