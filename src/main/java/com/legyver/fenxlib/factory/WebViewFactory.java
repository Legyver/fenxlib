package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.util.GuiUtil;
import javafx.scene.web.WebView;

public class WebViewFactory implements NodeFactory<WebView> {

	@Override
	public WebView makeNode(LocationContext lc) throws CoreException {
		WebView webView = new WebView();
		GuiUtil.getComponentRegistry().register(lc, webView);
		return webView;
	}

}
