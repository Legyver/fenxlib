package com.legyver.fenxlib.core.files.marshal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.core.files.marshal.converter.ConverterPlugin;
import org.apache.commons.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Marshaller for JSON
 */
public abstract class AbstractJsonFileMarshal implements FileMarshal {

    @Override
    public void marshal(Object content, FileOptions fileOptions) throws CoreException {
        String value = getConverter().convert(content);
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOptions.getFile());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ) {
            IOUtils.write(value.getBytes(StandardCharsets.UTF_8), bufferedOutputStream);
        } catch (IOException ioException) {
            throw new CoreException(ioException);
        }
    }

    /**
     * Get the converter that is responsible for converting the Object into json
     * @return the converter
     */
    protected abstract ConverterPlugin<String> getConverter();
}
