package com.legyver.fenxlib.widgets.filetree.event;

import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Import a directory into the tree when selected
 */
public class ImportDirectoryConsumer implements ThrowingConsumer<FileOptions> {
    private static final Logger logger = LogManager.getLogger(ImportDirectoryConsumer.class);
    private final FileTreeRegistry<IFileReference> fileTreeRegistry;

    /**
     * Construct a consumer that imports a directory into the file tree
     * @param fileTreeRegistry the registry to register any selected files with
     */
    public ImportDirectoryConsumer(FileTreeRegistry<IFileReference> fileTreeRegistry) {
        this.fileTreeRegistry = fileTreeRegistry;
    }

    @Override
    public void accept(FileOptions fileOptions) {
        logger.debug("Adding selected directory [{}] to file tree registry: {}", fileOptions.getFileName(), fileTreeRegistry);
        fileTreeRegistry.addToRoot(new FileReference(fileOptions.getFile()));
    }
}
