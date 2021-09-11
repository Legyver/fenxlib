package com.legyver.fenxlib.core.api.event.handlers;

import com.legyver.fenxlib.core.api.event.correlation.CorrelatingEventUtil;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.UUID;

/**
 * Shoe-horn correlationIds to event handlers.
 * If the action is something that initiates a series of events, make sure that this type of event handler is used so everything traces back to the event.
 * @param <T> the type of event
 */
public interface CorrelatingEventHandler<T extends Event> extends EventHandler<T> {


    default void handle(T event) {
        String correlationId = UUID.randomUUID().toString();
        CorrelatingEventUtil.setCorrelationIdOnThreadContext(correlationId);
        handleEvent(event, correlationId);
    }

    /**
     * Handle the event.
     * @param event the event to handle
     * @param correlationId the correlationId of the event
     */
    void handleEvent(T event, String correlationId);
}
