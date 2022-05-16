package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

/**
 * Factory to create a Hyperlink
 */
public class SystemBrowserHyperlinkFactory implements StyleableFactory<Hyperlink> {
	/**
	 * Constructor parameter providing the URL to use for the hyperlink
	 */
	public static final String URL = "url";

	private final HostServices hostServices;
	private final String url;

	/**
	 * Construct a factory that produces hyperlinks to open the link in a OS-default browser window
	 * @param url the url to open
	 * @param hostServices reference to the OS browser services
	 */
	public SystemBrowserHyperlinkFactory(String url, HostServices hostServices) {
		this.url = url;
		this.hostServices = hostServices != null ? hostServices : Fenxlib.getHostServices();
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
