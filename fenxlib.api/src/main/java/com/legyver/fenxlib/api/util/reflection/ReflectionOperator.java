package com.legyver.fenxlib.api.util.reflection;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Operator to get/set the value of a named field
 */
public class ReflectionOperator {
    private static final Logger logger = LogManager.getLogger(ReflectionOperator.class);

    private final Object object;

    /**
     * Construct an operator to perform reflection on the specified object.
     * @param object The object to set the value on
     */
    public ReflectionOperator(Object object) {
        this.object = object;
    }

    /**
     * Set the value of the field identified by the field name
     * @param fieldName the name of the field to set
     * @param value the value to set
     */
    public void setValue(String fieldName, Object value) {
        try {
            Field field = FieldUtils.getField(object.getClass(), fieldName, true);
            FieldUtils.writeField(field, object, value, true);
        } catch (IllegalAccessException e) {
           logger.error(e);
        }
    }

    /**
     * Get the value of the field identified by the field name
     * @param fieldName the name of the field
     * @return the value of the field
     */
    public Object getValue(String fieldName) {
        try {
            Field field = FieldUtils.getField(object.getClass(), fieldName, true);
            return FieldUtils.readField(field, object, true);
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
        return null;
    }
}
