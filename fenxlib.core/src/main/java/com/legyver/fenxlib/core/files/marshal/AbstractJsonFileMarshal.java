package com.legyver.fenxlib.core.files.marshal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.files.marshal.FileMarshalService;
import com.legyver.fenxlib.api.io.content.StringContentWrapper;
import com.legyver.fenxlib.core.files.marshal.converter.ConverterPlugin;

/**
 * Marshaller for JSON
 */
public abstract class AbstractJsonFileMarshal implements FileMarshalService<StringContentWrapper> {

    @Override
    public StringContentWrapper marshal(Object content, FileOptions fileOptions) throws CoreException {
        String value = getConverter().convert(content);
        return new StringContentWrapper(value);
    }

    /**
     * Get the converter that is responsible for converting the Object into json
     * @return the converter
     */
    protected abstract ConverterPlugin<String> getConverter();
}
