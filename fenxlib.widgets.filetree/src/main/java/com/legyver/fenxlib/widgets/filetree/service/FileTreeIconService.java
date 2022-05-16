package com.legyver.fenxlib.widgets.filetree.service;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.api.service.OrderedService;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;

/**
 * Provide the icons for the file tree
 */
public interface FileTreeIconService extends OrderedService<FileTreeIconService> {
    /**
     * Get the icon code for a file of type
     * @param fileReference the file reference to help determine the icon
     * @return the icon code
     */
    IconOptions getCodeForFile(IFileReference fileReference);

    /**
     * Get the icon code for a directory
     * @param fileReference the file reference to help determine the icon
     * @return the icon code
     */
    IconOptions getCodeForDirectory(IFileReference fileReference);
}
