package com.legyver.fenxlib.api.controls.factory.adapters;

/**
 * Adapter to set the value of an integer only if the value is not null
 */
public class IntegerAdapter extends NotNullAdapter<Integer> {
    /**
     * Construct an adapter to set values on a control only if the values to be set are not null.
     *
     * @param value the value to be set if not null
     */
    public IntegerAdapter(Integer value) {
        super(value);
    }
}
