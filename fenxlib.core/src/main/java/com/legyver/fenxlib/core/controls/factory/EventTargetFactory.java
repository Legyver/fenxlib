package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.core.controls.options.EventTargetControlOptions;
import javafx.event.EventTarget;

/**
 * Create an event target node
 * Use when the widget is not also a subtype of {@link javafx.scene.Node}
 * @param <T> the type of the widgets
 */
public interface EventTargetFactory<T extends EventTarget, U extends EventTargetControlOptions<T>> extends ControlFactory<T,U> {

}
