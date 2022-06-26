package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.event.EventHandler;

public interface EventHandlerMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T eventHandler(EventHandler eventHandler)  {
        new ReflectionOperator(builder()).setValue("eventHandler", eventHandler);
        return builder();
    }

    default EventHandler getEventHandler() {
        return (EventHandler) new ReflectionOperator(builder()).getValue("eventHandler");
    }
}
