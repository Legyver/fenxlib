package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.web.WebView;


/**
 * Factory to create a WebView
 */
public class WebViewFactory implements NodeFactory<WebView> {

	@Override
	public WebView makeNode(LocationContext lc) throws CoreException {
		WebView webView = new WebView();
		ApplicationContext.getComponentRegistry().register(lc, webView);
		return webView;
	}

}
