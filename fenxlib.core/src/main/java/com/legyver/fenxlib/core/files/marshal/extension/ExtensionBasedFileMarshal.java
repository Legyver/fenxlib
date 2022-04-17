package com.legyver.fenxlib.core.files.marshal.extension;

import com.legyver.fenxlib.core.files.marshal.FileMarshal;

/**
 * Marshaller that registers by the file extension.
 * For example, if a file has a .json extension it will use the {@link JsonExtensionFileMarshal}
 */
public interface ExtensionBasedFileMarshal extends FileMarshal {
    /**
     * Get the file extension supported by this marshaller
     * @return the supported file suffix
     */
    String getSupportedFileSuffix();

}
