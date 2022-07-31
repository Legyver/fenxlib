package com.legyver.fenxlib.api.controls.options;

import javafx.event.EventTarget;

/**
 * Tagging interface for options that produce a control of type EventTarget
 * @param <T> the type of the control being produced
 */
public interface EventTargetControlOptions<T extends EventTarget> extends ControlOptions<T> {
}
