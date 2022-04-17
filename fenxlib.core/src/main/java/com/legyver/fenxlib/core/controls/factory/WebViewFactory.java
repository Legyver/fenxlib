package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.web.WebView;


/**
 * Factory to create a WebView
 */
public class WebViewFactory implements StyleableFactory<WebView> {

	@Override
	public WebView makeNode(LocationContext lc) throws CoreException {
		WebView webView = makeWebView();
		ApplicationContext.getComponentRegistry().register(lc, webView);
		return webView;
	}

	/**
	 * Factory method to init a new WebView.
	 * By default, returns a javafx {@link WebView}.  Override if you want a specific library's implementation
	 * @return the web view
	 */
	protected WebView makeWebView() {
		return new WebView();
	}

}
