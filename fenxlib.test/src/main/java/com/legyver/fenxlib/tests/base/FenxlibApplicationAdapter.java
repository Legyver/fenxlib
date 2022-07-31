package com.legyver.fenxlib.tests.base;
import com.legyver.fenxlib.api.context.ApplicationContext;
import javafx.application.Application;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationFixture;

/**
 * A Test Application Adapter for Fenxlib test cases.
 * Aside from delegating the TestFX {@link ApplicationFixture}, it also registers the primary stage with the ApplicationContext.
 * This is a slight deviation from a non-test application, where the primary stage is registered during {@link com.legyver.fenxlib.api.config.options.ApplicationOptions#startup(Application, Stage)}
 */
public class FenxlibApplicationAdapter extends Application {
    private final ApplicationFixture applicationFixture;

    /**
     * Construct an application adapter for the test fixture
     * @param applicationFixture the test fixture to adapt
     */
    public FenxlibApplicationAdapter(ApplicationFixture applicationFixture) {
        this.applicationFixture = applicationFixture;
    }

    @Override
    public void init() throws Exception {
        applicationFixture.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationFixture.start(primaryStage);
        ApplicationContext.registerStage(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        applicationFixture.stop();
    }
}
