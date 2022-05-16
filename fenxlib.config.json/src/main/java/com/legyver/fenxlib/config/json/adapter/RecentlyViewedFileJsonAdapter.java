package com.legyver.fenxlib.config.json.adapter;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;
import com.legyver.fenxlib.config.json.parts.MapSyncableRecentlyViewedFile;

import java.io.File;

/**
 * Adapter to adapt a recently viewed file to the appropriate reference format for the config file
 */
public class RecentlyViewedFileJsonAdapter implements IConfigAdapter<File, MapSyncableRecentlyViewedFile> {
    @Override
    public MapSyncableRecentlyViewedFile adapt(File arg) throws CoreException {
        return MapSyncableRecentlyViewedFile.parse(arg);
    }
}
