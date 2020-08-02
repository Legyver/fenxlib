package com.legyver.fenxlib.core.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

public class SystemBrowserHyperlinkFactory implements NodeFactory<Hyperlink> {

	private final HostServices hostServices;
	private final String url;

	public SystemBrowserHyperlinkFactory(String url, HostServices hostServices) {
		this.url = url;
		this.hostServices = hostServices;
	}

	@Override
	public Hyperlink makeNode(LocationContext lc) throws CoreException {
		Hyperlink link = new Hyperlink();
		link.setText("Powered by open source");
		link.setOnAction((ActionEvent event) -> {
			hostServices.showDocument(url);
		});
		return link;
	}

}
