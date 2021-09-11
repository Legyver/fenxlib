package com.legyver.fenxlib.core.api.event.correlation;

import com.legyver.fenxlib.core.api.event.handlers.CorrelatingEventHandler;
import com.legyver.fenxlib.core.api.event.handlers.CorrelatingEventHandlerWrapper;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Wrap EventHandlers with a correlationId-generating wrapper
 */
public class CorrelatingEventHandlerFactory {

    /**
     * Wrap EventHandlers with a correlationId-generating wrapper.
     * This factory method is intended to mitigate against multiple CorrelatingEventHandlers overwriting each other's correlationIds.
     *
     * @param eventHandler event handler to wrap, if of instance CorrelatingEventHandler, it is returned, otherwise it is wrapped.
     * @param <T> the type of the event
     * @return a correlating event handler to inject a UUID onto a ThreadContext
     */
     public static <T extends Event> CorrelatingEventHandler<T> wrapIfNecessary(EventHandler<T> eventHandler) {
        if (eventHandler instanceof  CorrelatingEventHandler) {
            return (CorrelatingEventHandler<T>) eventHandler;
        }
        return new CorrelatingEventHandlerWrapper<>(eventHandler);
    }
}
