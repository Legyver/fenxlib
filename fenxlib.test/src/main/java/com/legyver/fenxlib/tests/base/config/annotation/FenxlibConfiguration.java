package com.legyver.fenxlib.tests.base.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to support specifying a custom Fenxlib application configuration.
 * This will load your application config into the Fenxlib framework.
 *
 * Example use for using a custom config file:
 * <pre>
 * &#64;FenxlibConfiguration(configFile = "test_application_config.json")
 * public void MyIntegrationTest extends FenxlibTest {
 *    &#64;Test
 *    public void testCase1() {
 *       //test code
 *    }
 * }
 * </pre>
 *
 * Example use for using a custom resource bundle
 * <pre>
 *     @FenxlibConfiguration(resourceBundles = {
 *         "it.core.i18n.test"
 *     })
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FenxlibConfiguration {
    /**
     * The application config json to load
     * If the value starts with '/', it will be assumed to be an absolute path as is customary with Java resources
     * Failing this it will attempt to resolve it against a config directory at the root of the classpath (ie: src/test/resources/config/test_application.json)
     * @return the name of the resource to load the config from
     */
    String configFile() default "";

    /**
     * The resource bundles to be loaded by the test config
     *
     * @return the resource bundles to load
     */
    String[] resourceBundles() default {};
}
