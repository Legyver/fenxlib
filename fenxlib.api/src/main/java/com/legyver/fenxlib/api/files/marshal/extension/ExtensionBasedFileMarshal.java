package com.legyver.fenxlib.api.files.marshal.extension;

import com.legyver.fenxlib.api.files.marshal.FileMarshalService;
import com.legyver.fenxlib.api.io.content.OutputContentWrapper;

/**
 * Marshaller that registers by the file extension.
 * For example, if a file has a .json extension it will use the JsonExtensionFileMarshal
 */
public interface ExtensionBasedFileMarshal<T extends OutputContentWrapper> extends FileMarshalService<T> {
    /**
     * Get the file extension supported by this marshaller
     * @return the supported file suffix
     */
    String getSupportedFileSuffix();

}
