package com.legyver.fenxlib.api.files.marshal.contenttype;

import com.legyver.fenxlib.api.files.marshal.FileMarshalService;
import com.legyver.fenxlib.api.io.content.OutputContentWrapper;

/**
 * Marshaller that registers by the content-type it handles
 */
public interface ContentTypeBasedFileMarshal<T extends OutputContentWrapper> extends FileMarshalService<T> {
    /**
     * Get the content-type this marshaller handles
     * @return the supported content-type
     */
    String getSupportedContentType();
}
