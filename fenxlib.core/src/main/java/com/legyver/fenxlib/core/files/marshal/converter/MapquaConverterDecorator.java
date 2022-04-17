package com.legyver.fenxlib.core.files.marshal.converter;

import com.legyver.core.exception.CoreException;
import com.legyver.utils.mapqua.mapbacked.MapSyncable;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;

/**
 * Decorator to auto-sync Mapqua entities to the backing map prior to conversion
 */
public class MapquaConverterDecorator extends AbstractConverterDecorator<String> {

    /**
     * Construct a Mapque Converter to decorate the other converter
     * @param converterPlugin the other converter to decorate
     */
    public MapquaConverterDecorator(ConverterPlugin<String> converterPlugin) {
        super(converterPlugin);
    }

    @Override
    public String convert(Object object) throws CoreException {
        //preprocess object before it's converted
        Object value = object;
        if (value instanceof RawMapAware) {
            if (value instanceof MapSyncable) {
                ((MapSyncable) value).sync();
            }
            value = ((RawMapAware) value).getRawMap();
        }
        return converterPlugin.convert(value);
    }
}
