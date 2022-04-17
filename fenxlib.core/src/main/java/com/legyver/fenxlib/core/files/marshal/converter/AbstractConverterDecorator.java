package com.legyver.fenxlib.core.files.marshal.converter;

/**
 * Base decorator for a {@link ConverterPlugin}
 * @param <T> the type of the output of the converter
 */
public abstract class AbstractConverterDecorator<T> implements ConverterPlugin<T> {
    /**
     * The decorated converter
     */
    protected final ConverterPlugin<T> converterPlugin;

    /**
     * Decorate a converter
     * @param converterPlugin the converter to decorate
     */
    protected AbstractConverterDecorator(ConverterPlugin<T> converterPlugin) {
        this.converterPlugin = converterPlugin;
    }
}
