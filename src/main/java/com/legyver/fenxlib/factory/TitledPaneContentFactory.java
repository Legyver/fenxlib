package com.legyver.fenxlib.factory;

import com.sun.javafx.scene.control.skin.TitledPaneSkin;
import javafx.scene.control.Skin;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

public interface TitledPaneContentFactory<T extends Pane> extends NodeFactory<T> {

	default Skin<?> skin(TitledPane titledPane) {
		return new TitledPaneSkin(titledPane);//default skin
	}
}
