package com.legyver.fenxlib.extensions.tuktukfx.task;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.event.correlation.CorrelatingEventUtil;
import com.legyver.tuktukfx.adapter.TaskStatusAdapter;
import com.legyver.tuktukfx.task.AbstractAbortableTask;
import org.apache.logging.log4j.ThreadContext;

/**
 * Abstract class that injects the correlationId into the thread context.
 * @param <R> the return type of the task
 * @param <U> the context
 */
public abstract class AbstractCorrelatingObservableTask<R, U extends CorrelatingTaskContext> extends AbstractAbortableTask<R, U> {
    /**
     * Pass in the main task context here.  This is exists mainly as a way to pass arguments to your task processor.
     * However, it also serves to communicate the domain/range size to any observers for status reporting.
     *
     * @param context : context for the task
     */
    public AbstractCorrelatingObservableTask(U context) {
        super(context);
    }

    @Override
    public void process(TaskStatusAdapter adapter, double domain) throws CoreException {
        ThreadContext.put(CorrelatingEventUtil.CORRELATION_ID, context.getCorrelationId());
        super.process(adapter, domain);
    }
}
