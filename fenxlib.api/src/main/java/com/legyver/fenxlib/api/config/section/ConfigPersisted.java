package com.legyver.fenxlib.api.config.section;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Mark a field as being part of the configuration to be persisted with the configuration.
 * The field must be of type {@link ConfigSection}.
 * As a best-practice, a module or application should bundle all their configuration values in a single
 * ConfigSection.
 * Your {@link com.legyver.fenxlib.api.config.ApplicationConfig} may/should contain multiple @Configuration
 * as appropriate.  the fenxlib.core module has a ConfigSection, you may want to leverage.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigPersisted {

}
