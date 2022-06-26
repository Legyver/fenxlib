package com.legyver.fenxlib.core.util.reflection;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionOperator {
    private static final Logger logger = LogManager.getLogger(ReflectionOperator.class);

    private final Object object;

    public ReflectionOperator(Object object) {
        this.object = object;
    }

    public void setValue(String fieldName, Object value) {
        try {
            Field field = FieldUtils.getField(object.getClass(), fieldName, true);
            FieldUtils.writeField(field, object, value, true);
        } catch (IllegalAccessException e) {
           logger.error(e);
        }
    }

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
