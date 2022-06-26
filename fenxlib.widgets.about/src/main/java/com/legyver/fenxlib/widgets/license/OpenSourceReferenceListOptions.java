package com.legyver.fenxlib.widgets.license;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;

import java.util.Properties;

public class OpenSourceReferenceListOptions extends BaseControlBuilder<OpenSourceReferenceListOptions> implements StyleableControlOptions<OpenSourceReferenceList> {
    private Properties properties;

    public OpenSourceReferenceListOptions properties(Properties properties) {
        this.properties = properties;
        return me();
    }

    public Properties getProperties() {
        return properties;
    }
}
