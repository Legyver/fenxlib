package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.factory.StyleableFactory;
import javafx.scene.control.Tab;

/**
 * Factory wrapper to embed content in a tab
 * @param <T> the sub-type of the tab
 */
public interface TabContentFactory<T extends Tab> extends StyleableFactory<T> {
	
}
