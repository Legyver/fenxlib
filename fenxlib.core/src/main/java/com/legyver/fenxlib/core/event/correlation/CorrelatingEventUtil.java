package com.legyver.fenxlib.core.event.correlation;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.ThreadContext;

import java.util.Map;

/**
 * Set and retrieve the correlation id on the ThreadContext.
 *
 * Include in your log messages using %X{CorrelationId}
 */
public class CorrelatingEventUtil {
    /**
     * The property key for the ThreadContext value
     */
    public static String CORRELATION_ID = "CorrelationId";

    /**
     * Retrieve the correlation id from the thread context
     * @return the correlation id
     */
    public static String getCorrelationIdFromThreadContext() {
        final Map<String, String> values = ThreadContext.getImmutableContext();
        return values.get(CORRELATION_ID);
    }

    /**
     * Set the correlation id on the thread context
     * @param correlationId the correlation id to set
     */
    public static void setCorrelationIdOnThreadContext(String correlationId) {
        CloseableThreadContext.put(CORRELATION_ID, correlationId);
    }
}
