package com.legyver.fenxlib.core.impl.web;

import javafx.scene.control.Hyperlink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

/**
 * Open a link that opens in the user's default web browser
 */
public class DesktopWeblink extends Hyperlink {
    private static final Logger logger = LogManager.getLogger(DesktopWeblink.class);

    /**
     * Construct a link that opens in the user's default web browser
     * @param text the text for the link
     * @param linkUrl the link url
     */
    public DesktopWeblink(String text, String linkUrl) {
        super(text);
        setOnAction(click -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(linkUrl));
            } catch (Exception e) {
                logger.error(e);
            }
        });
    }
}
