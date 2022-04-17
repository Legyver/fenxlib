package com.legyver.fenxlib.extensions.tuktukfx.task;

import com.legyver.fenxlib.core.event.correlation.CorrelatingEventUtil;
import com.legyver.tuktukfx.status.TaskTimingData;

/**
 * Task context that supplies a correlationId
 */
public class CorrelatingTaskContext extends TaskTimingData {
    private final String correlationId;

    /**
     * Instantiate the task context with timing data
     *
     * @param sizeGuess : best-guess for domain size used in progress indicators
     * @param correlationId: correlationId of triggering event
     */
    public CorrelatingTaskContext(double sizeGuess, String correlationId) {
        super(sizeGuess);
        this.correlationId = correlationId;
    }

    /**
     * Instantiate the task context with timing data and the correlationId set on the thread context
     *
     * @param sizeGuess : best-guess for domain size used in progress indicators
     */
    public CorrelatingTaskContext(double sizeGuess) {
        this(sizeGuess, CorrelatingEventUtil.getCorrelationIdFromThreadContext());
    }

    /**
     * Get the set correlation id
     * @return the correlation id
     */
    public String getCorrelationId() {
        return correlationId;
    }
}
