package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

/**
 * Factory to create a Hyperlink
 */
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
