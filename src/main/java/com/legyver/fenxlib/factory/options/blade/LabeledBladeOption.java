package com.legyver.fenxlib.factory.options.blade;

import javafx.beans.Observable;

public interface LabeledBladeOption<T extends Observable> extends BladeOption<T> {

	String getLabel();

	int getLabelSpan();
}
