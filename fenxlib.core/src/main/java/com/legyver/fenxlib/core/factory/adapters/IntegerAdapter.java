package com.legyver.fenxlib.core.factory.adapters;

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
