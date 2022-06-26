package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import javafx.application.HostServices;
import javafx.scene.control.Hyperlink;

public class HyperlinkOptions extends BaseControlBuilder<HyperlinkOptions> implements StyleableControlOptions<Hyperlink> {
    private String url;
    private HostServices hostServices;

    public HyperlinkOptions url(String url) {
        this.url = url;
        return me();
    }

    public HyperlinkOptions hostServices(HostServices hostServices) {
        this.hostServices = hostServices;
        return me();
    }

    public String getUrl() {
        return url;
    }

    public HostServices getHostServices() {
        return hostServices;
    }
}
