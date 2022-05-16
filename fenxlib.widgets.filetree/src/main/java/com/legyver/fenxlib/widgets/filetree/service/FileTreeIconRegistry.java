package com.legyver.fenxlib.widgets.filetree.service;

import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;

import java.util.*;

/**
 * Registry of file tree icon services.
 * Provide your own FileTreeIconService with a lower than 0 order to override {@link DefaultFileTreeIconService}
 */
public class FileTreeIconRegistry {
    private final OrderedServiceDelegator<FileTreeIconService> orderedServiceDelegator;
    private static FileTreeIconRegistry instance;

    private FileTreeIconRegistry() {
        ServiceLoader<FileTreeIconService> serviceLoader = ServiceLoader.load(FileTreeIconService.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(serviceLoader);
    }

    /**
     * Retrieve a singleton instance of the registry
     * @return the instance
     */
    public static FileTreeIconRegistry getInstance() {
        if (instance == null) {
            synchronized (FileTreeIconRegistry.class) {
                if (instance == null) {
                    instance = new FileTreeIconRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Get the icon associated with a file type.
     * Uses the service with the highest preference (lowest {@link FileTreeIconService#order()})
     * that returns a not-null icon
     * @param fileReference the file reference to use to determine the appropriate icon
     * @return the appropriate icon
     */
    public IconOptions getIcon(IFileReference fileReference) {
        IconOptions foundIcon = null;

        for (Iterator<FileTreeIconService> it = orderedServiceDelegator.iterator(); it.hasNext(); ) {
            FileTreeIconService fileTreeIconService = it.next();
            if (fileReference.isDirectory()) {
                foundIcon = fileTreeIconService.getCodeForDirectory(fileReference);
            } else {
                foundIcon = fileTreeIconService.getCodeForFile(fileReference);
            }
            if (foundIcon != null) {
                break;
            }
        }
        return foundIcon;
    }
}
