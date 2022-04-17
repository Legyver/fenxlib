package com.legyver.fenxlib.core.files.marshal.converter;

import com.legyver.core.exception.CoreException;
import com.legyver.utils.jackiso.JacksonObjectMapper;

/**
 * Converter to convert an Object to a json string
 */
public class JsonConverterPlugin implements ConverterPlugin<String> {
    @Override
    public String convert(Object object) throws CoreException {
        return JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(object);
    }
}
