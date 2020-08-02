package com.legyver.fenxlib.core.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.web.WebView;

public class WebViewFactory implements NodeFactory<WebView> {

	@Override
	public WebView makeNode(LocationContext lc) throws CoreException {
		WebView webView = new WebView();
		ApplicationContext.getComponentRegistry().register(lc, webView);
		return webView;
	}

}
