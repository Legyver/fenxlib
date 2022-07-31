package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.HyperlinkOptions;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

/**
 * Factory to create a Hyperlink
 */
public class SystemBrowserHyperlinkFactory implements StyleableFactory<Hyperlink, HyperlinkOptions> {

	@Override
	public Hyperlink makeNode(LocationContext lc, HyperlinkOptions options) throws CoreException {
		Hyperlink link = new Hyperlink();
		link.setText("Powered by open source");
		link.setOnAction((ActionEvent event) -> {
			options.getHostServices().showDocument(options.getUrl());
		});
		return link;
	}

	@Override
	public HyperlinkOptions newOptions() {
		return new HyperlinkOptions();
	}

}
