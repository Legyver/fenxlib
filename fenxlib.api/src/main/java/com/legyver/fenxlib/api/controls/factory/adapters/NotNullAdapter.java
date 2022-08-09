package com.legyver.fenxlib.api.controls.factory.adapters;

import java.util.function.Consumer;

/**
 * Adapter to set values on a control only if the values to be set are not null.
 * This will then allow for the default control mechanism of handling unspecified values to be respected
 * @param <T> the type of the value to be set
 */
public class NotNullAdapter<T> {
    private final T value;

    /**
     * Construct an adapter to set values on a control only if the values to be set are not null.
     * @param value the value to be set if not null
     */
    public NotNullAdapter(T value) {
        this.value = value;
    }

    /**
     * Set the value with the provided setter if the value is not null
     * @param setter the setter to use to set the value
     */
    public void setNotNull(Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
