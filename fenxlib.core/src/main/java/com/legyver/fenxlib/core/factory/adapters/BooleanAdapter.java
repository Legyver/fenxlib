package com.legyver.fenxlib.core.factory.adapters;

/**
 * Null-safe adapter for boolean values
 */
public class BooleanAdapter extends NotNullAdapter<Boolean> {

    /**
     * Construct an adapter for boolean values
     * @param value the value to adapt.
     *              If the value is null, the value will not be set to respect the default handling of the widget
     */
    public BooleanAdapter(Boolean value) {
        super(value);
    }

}
