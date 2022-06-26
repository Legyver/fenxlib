package com.legyver.fenxlib.core.util.binding;

/**
 * Type of binding to apply
 */
public enum BindType {
    /**
     * A bi-directional binding
     */
    BIDIRECTIONAL,
    /**
     * A binding where the control property is bound to an external property
     */
    CONTROL_BOUND_TO_PROPERTY,
    /**
     * A binding where the external property is bound to the control property
     */
    PROPERTY_BOUND_TO_CONTROL
}
