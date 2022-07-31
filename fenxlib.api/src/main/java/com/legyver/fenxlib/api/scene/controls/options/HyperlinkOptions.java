package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import javafx.application.HostServices;
import javafx.scene.control.Hyperlink;

/**
 * Options for a JavaFX Hyperlink
 */
public class HyperlinkOptions extends BaseControlBuilder<HyperlinkOptions> implements StyleableControlOptions<Hyperlink> {
    private String url;
    private HostServices hostServices;

    /**
     * Specify the url for the hyperlink
     * @param url the url
     * @return the builder using this mixin
     */
    public HyperlinkOptions url(String url) {
        this.url = url;
        return me();
    }

    /**
     * Specify the host services
     * @param hostServices the host services on the client machine
     * @return the builder using this mixin
     */
    public HyperlinkOptions hostServices(HostServices hostServices) {
        this.hostServices = hostServices;
        return me();
    }

    /**
     * Get the url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the host services
     * @return the host services
     */
    public HostServices getHostServices() {
        return hostServices;
    }
}
