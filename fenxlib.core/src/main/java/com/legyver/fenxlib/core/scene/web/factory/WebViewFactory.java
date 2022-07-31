package com.legyver.fenxlib.core.scene.web.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.scene.web.options.WebViewOptions;
import javafx.scene.web.WebView;


/**
 * Factory to create a WebView
 */
public class WebViewFactory implements StyleableFactory<WebView, WebViewOptions> {

	@Override
	public WebView makeNode(LocationContext lc, WebViewOptions options) throws CoreException {
		WebView webView = makeWebView();
		ApplicationContext.getComponentRegistry().register(lc.decorateWith(options.getName()), webView);
		return webView;
	}

	@Override
	public WebViewOptions newOptions() {
		return new WebViewOptions();
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
