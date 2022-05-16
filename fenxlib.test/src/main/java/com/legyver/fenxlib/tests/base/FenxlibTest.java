package com.legyver.fenxlib.tests.base;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.options.ApplicationOptions;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.IApplicationLifecycleHookRegistry;
import com.legyver.fenxlib.api.lifecycle.ResettableApplicationLifecycleHookRegistry;
import com.legyver.fenxlib.tests.base.config.TestApplicationOptionsBuilder;
import com.legyver.fenxlib.tests.base.config.TestApplicationResource;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit5.ApplicationAdapter;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Base test that
 * 1. Starts up the application framework before any test runs
 * 2. Resets file registry open files between tests
 */
public class FenxlibTest extends ApplicationTest {
    /**
     * Startup the application with the ApplicationFixture adapter
     * @throws CoreException if there is an error doing so
     */
    @BeforeEach
    public void fenxlibBeforeEach() throws CoreException {
        FenxlibConfiguration fenxlibConfiguration = getClass().getAnnotation(FenxlibConfiguration.class);
        TestApplicationOptionsBuilder builder = TestApplicationOptionsBuilder.defaultBuilder();
        if (fenxlibConfiguration != null && validateAnnotatedConfiguration(fenxlibConfiguration)) {
            builder.customAppConfigProvider(new TestApplicationResource(fenxlibConfiguration.value()));
        }
        ApplicationOptions applicationOptions = builder.build();
        applicationOptions.startup(new ApplicationAdapter(this));
    }

    private boolean validateAnnotatedConfiguration(FenxlibConfiguration fenxlibConfiguration) {
        return fenxlibConfiguration.value() != null && fenxlibConfiguration.value() != null && !fenxlibConfiguration.value().equals("");
    }

    /**
     * Reset the Application lifecycle between tests
     * @throws CoreException if there is error doing so
     */
    @AfterEach
    public void fenxlibReset() throws CoreException {
        IApplicationLifecycleHookRegistry registry = ApplicationContext.getApplicationLifecycleHookRegistry();
        if (registry instanceof ResettableApplicationLifecycleHookRegistry) {
            ((ResettableApplicationLifecycleHookRegistry) registry).reset();
        }
    }
}
