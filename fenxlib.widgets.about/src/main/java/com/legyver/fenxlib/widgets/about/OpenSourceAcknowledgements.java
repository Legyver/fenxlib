package com.legyver.fenxlib.widgets.about;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.Properties;

/**
 * Control displaying OpenSource acknowledgements as read from a property file
 *
 * The tagline can be passed in via properties, ex:
 *  open.source.thankyou.message=Thank you to open source!
 * If none is specified, the default label will be used
 *  legyver.defaults.label.about.powered.by.clause=Powered by open source
 */
public class OpenSourceAcknowledgements extends Control {
    private final String openSourceTagLine;
    private final Properties openSourceLicenseProperties;

    /**
     * Construct a control to display open source acknowledgements
     * @param openSourceTagLine the tagline
     * @param openSourceLicenseProperties the property file containing open source license information
     */
    public OpenSourceAcknowledgements(String openSourceTagLine, Properties openSourceLicenseProperties) {
        this.openSourceTagLine = openSourceTagLine;
        this.openSourceLicenseProperties = openSourceLicenseProperties;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new OpenSourceAcknowledgementsSkin(this);
    }

    /**
     * Get the tagline to precede the open source library list
     * @return the tagline
     */
    public String getOpenSourceTagLine() {
        return openSourceTagLine;
    }

    /**
     * Get the open source license properties
     * @return the properties with the open source license information
     */
    public Properties getOpenSourceLicenseProperties() {
        return openSourceLicenseProperties;
    }
}
