package com.legyver.fenxlib.core.factory.adapters;

public class DoubleAdapter extends NotNullAdapter<Double> {
    /**
     * Construct an adapter to set values on a control only if the values to be set are not null.
     *
     * @param value the value to be set if not null
     */
    public DoubleAdapter(Double value) {
        super(value);
    }
}
