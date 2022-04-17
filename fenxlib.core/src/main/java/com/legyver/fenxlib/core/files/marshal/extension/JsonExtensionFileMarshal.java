package com.legyver.fenxlib.core.files.marshal.extension;

import com.legyver.fenxlib.core.files.marshal.AbstractJsonFileMarshal;
import com.legyver.fenxlib.core.files.marshal.converter.ConverterPlugin;
import com.legyver.fenxlib.core.files.marshal.converter.JsonConverterPlugin;
import com.legyver.fenxlib.core.files.marshal.converter.MapquaConverterDecorator;

/**
 * Marshaller to marshal content to a .json file
 */
public class JsonExtensionFileMarshal extends AbstractJsonFileMarshal implements ExtensionBasedFileMarshal {
    private final ConverterPlugin<String> converter = new MapquaConverterDecorator(new JsonConverterPlugin());

    @Override
    public String getSupportedFileSuffix() {
        return ".json";
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    protected ConverterPlugin<String> getConverter() {
        return converter;
    }
}
