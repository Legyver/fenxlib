package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.css.Styleable;

/**
 * Create a styleable widget.
 * Use when the widget is not also a subtype of {@link javafx.scene.Node}
 * @param <T> the type of the widgets
 */
public interface StyleableFactory<T extends Styleable, U extends StyleableControlOptions<T>> extends ControlFactory<T,U> {
}
