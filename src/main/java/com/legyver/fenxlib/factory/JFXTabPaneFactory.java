package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXTabPane;
import javafx.scene.control.Tab;

public class JFXTabPaneFactory extends AbstractTabPaneFactory<JFXTabPane> {

	public JFXTabPaneFactory(String name, TabContentFactory<? extends Tab>... contentFactory) {
		super(name, contentFactory);
	}

	public JFXTabPaneFactory(TabContentFactory<? extends Tab>... contentFactory) {
		super(contentFactory);
	}

	@Override
	protected JFXTabPane newTabPane() {
		return new JFXTabPane();
	}
	
}
