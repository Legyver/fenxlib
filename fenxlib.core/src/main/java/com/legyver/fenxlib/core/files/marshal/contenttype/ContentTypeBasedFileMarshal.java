package com.legyver.fenxlib.core.files.marshal.contenttype;

import com.legyver.fenxlib.core.files.marshal.FileMarshal;

/**
 * Marshaller that registers by the content-type it handles
 */
public interface ContentTypeBasedFileMarshal extends FileMarshal {
    /**
     * Get the content-type this marshaller handles
     * @return the supported content-type
     */
    String getSupportedContentType();
}
