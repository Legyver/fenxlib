package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;
import javafx.event.EventHandler;

/**
 * Mixin to specify an EventHandler.
 * To use this mixin, the builder must have field of type EventHandler called "eventHandler"
 * @param <T> the type of the builder using this mixin
 */
public interface EventHandlerMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify the event handler
     * @param eventHandler the event handler
     * @return the builder using this mixin
     */
    default T eventHandler(EventHandler eventHandler)  {
        new ReflectionOperator(builder()).setValue("eventHandler", eventHandler);
        return builder();
    }

    /**
     * Get the event handler
     * @return the event handler
     */
    default EventHandler getEventHandler() {
        return (EventHandler) new ReflectionOperator(builder()).getValue("eventHandler");
    }
}
