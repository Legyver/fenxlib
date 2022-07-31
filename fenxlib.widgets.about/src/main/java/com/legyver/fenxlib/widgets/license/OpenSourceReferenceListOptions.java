package com.legyver.fenxlib.widgets.license;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;

import java.util.Properties;

/**
 * Options for a OpenSourceReferenceList control
 */
public class OpenSourceReferenceListOptions extends BaseControlBuilder<OpenSourceReferenceListOptions> implements StyleableControlOptions<OpenSourceReferenceList> {
    private Properties properties;

    /**
     * Construct options for an OpenSourceReferenceList with the specified properties
     * @param properties the properties containing the open source reference list and license data
     * @return this builder
     */
    public OpenSourceReferenceListOptions properties(Properties properties) {
        this.properties = properties;
        return me();
    }

    /**
     * Get the properties for the reference list
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }
}
