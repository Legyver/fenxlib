package com.legyver.fenxlib.core.api.event.handlers;

import com.legyver.fenxlib.core.api.event.correlation.CorrelatingEventUtil;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Decorate an EventHandler with a correlationId providing mechanism, so that when an event is triggered, its handler knows the correlation.
 * @param <T> the type of event
 */
public class CorrelatingEventHandlerWrapper<T extends Event> implements CorrelatingEventHandler<T> {
    private static final Logger logger = LogManager.getLogger(CorrelatingEventHandlerWrapper.class);
    private final EventHandler<T> eventHandler;

    /**
     * Wrap an EventHandler to automatically inject a new CorrelationId into the ThreadContext
     * @param eventHandler the event handler to wrap
     */
    public CorrelatingEventHandlerWrapper(EventHandler<T> eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(T event, String correlationId) {
        CorrelatingEventUtil.setCorrelationIdOnThreadContext(correlationId);
        if (logger.isTraceEnabled()) {
            logger.trace("Processing [{}] event with correlationId: {}", event.getEventType(), correlationId);
        }
        eventHandler.handle(event);
    }
}
