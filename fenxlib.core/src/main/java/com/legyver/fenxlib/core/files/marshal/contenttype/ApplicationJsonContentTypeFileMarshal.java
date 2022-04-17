package com.legyver.fenxlib.core.files.marshal.contenttype;

import com.legyver.fenxlib.core.files.marshal.AbstractJsonFileMarshal;
import com.legyver.fenxlib.core.files.marshal.converter.ConverterPlugin;
import com.legyver.fenxlib.core.files.marshal.converter.JsonConverterPlugin;
import com.legyver.fenxlib.core.files.marshal.converter.MapquaConverterDecorator;

/**
 * Marshaller for application/json that registers by that content-type
 */
public class ApplicationJsonContentTypeFileMarshal extends AbstractJsonFileMarshal implements ContentTypeBasedFileMarshal {
    /**
     * Type content-type this marshaller handles
     */
    public static final String APPLICATION_JSON = "application/json";

    private final ConverterPlugin<String> plugin = new MapquaConverterDecorator(new JsonConverterPlugin());

    @Override
    public String getSupportedContentType() {
        return APPLICATION_JSON;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    protected ConverterPlugin<String> getConverter() {
        return plugin;
    }
}
