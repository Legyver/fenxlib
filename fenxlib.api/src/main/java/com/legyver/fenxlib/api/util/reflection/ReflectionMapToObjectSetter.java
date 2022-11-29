package com.legyver.fenxlib.api.util.reflection;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Utility to set a value on an object
 */
public class ReflectionMapToObjectSetter {
    private static final Logger logger = LogManager.getLogger(ReflectionMapToObjectSetter.class);

    /**
     * Set the value on the bean
     * @param valueToSet the value to set
     * @param name the name of the variable to set
     * @param parentToSetOn the object to set the value on
     */
    public void setValue(Object valueToSet, String name, Object parentToSetOn) {
        if (valueToSet == null) {
            logger.debug("Value to set for field [{}] is null.", name);
            return;
        }
        Field field = null;
        boolean setValue = valueToSet.getClass().isPrimitive();
        logger.debug("Value to set [{}] for [{}] is primitive: {}", valueToSet, name, setValue);
        if (!setValue) {
            field = FieldUtils.getField(parentToSetOn.getClass(), name, true);
            if (field != null) {
                setValue = valueToSet.getClass().isAssignableFrom(field.getDeclaringClass());
                logger.debug("Value to set type [{}] is assignable from field type [{}]: {}", valueToSet.getClass(), field.getDeclaringClass(), setValue);
            }
        }
        if (setValue) {
            if (field == null) {
                field = FieldUtils.getField(parentToSetOn.getClass(), name, true);
            }
            if (field != null) {
                new ReflectionOperator(parentToSetOn).setValue(name, valueToSet);
                logger.debug("Value [{}] set on field: {}", valueToSet, name);
            }
        } else if (valueToSet instanceof Map) {
            logger.debug("Value to set of type map");
            if (field == null) {
                field = FieldUtils.getField(parentToSetOn.getClass(), name, true);
            }
            if (field != null) {
                Object currentValue = new ReflectionOperator(parentToSetOn).getValue(name);
                logger.debug("Current value of field [{}] is: {}", name, currentValue);
                Map map = (Map) valueToSet;

                for (Object o : map.keySet()) {
                    String key = (String) o;
                    Object value = map.get(o);
                    logger.debug("Processing map value [{}] for key: {}", value, key);
                    setValue(value, key, currentValue);
                }
            }
        } else {
            if (field == null) {
                field = FieldUtils.getField(parentToSetOn.getClass(), name, true);
            }
            if (field == null) {
                logger.warn("Do not know how to set value [{}] for field name [{}] as field does not exist", valueToSet, name);
            } else {
                logger.warn("Do not know how to set value [{}] for field name [{}] as field is of type: {}", valueToSet, name, field.getDeclaringClass());
            }
        }
    }
}
