package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.stream.Stream;

/**
 * Factory to create a Fenxlib Application Scene.
 * See {@link #makeContainer(Stage, BorderPane)} for customization
 */
public class SceneFactory {
    /**
     * The Fenxlib Main application stack pane
     */
    public static final String FENXLIB_MAIN_APPLICATION_PANE = "_FENXLIB_MAIN_";
    /**
     * The Fenxlib Alert Pane
     */
    public static final String FENXLIB_ALERT_PANE = "_FENXLIB_ALERT_PANE_";
    /**
     * The Fenxlib Popup Pane
     */
    public static final String FENXLIB_POPUP_PANE = "_FENXLIB_POPUP_PANE_";

    private final URL[] stylesheetUrls;
    private final Stage stage;
    private final double width;
    private final double height;

    /**
     * Construct a factory to create the scene with the appropriate size/width and load any stylesheets
     * @param stage the stage for the scene
     * @param width the default width for the scene
     * @param height the default height for the scene
     * @param stylesheetUrls any resources to be loaded that need to be incorporated into the scene
     */
    public SceneFactory(Stage stage, double width, double height, URL... stylesheetUrls) {
        this.stylesheetUrls = stylesheetUrls;
        this.stage = stage;
        this.width = width;
        this.height = height;
    }

    /**
     * Make the scene
     * @param root the main application layout border pane
     * @return the scene
     */
    public Scene makeScene(BorderPane root) {
        Pane decorator = makeContainer(stage, root);
        if (decorator == null) {
            decorator = root;
        }
        Scene scene = new Scene(decorator, width, height);
        if (stylesheetUrls != null) {
            Stream.of(stylesheetUrls).map(url -> url.toExternalForm())
                    .forEach(styleSheet -> scene.getStylesheets().add(styleSheet));
        }

        return scene;
    }

    /**
     * Hook to provide a custom decorator for the main screen.
     * By default, returns a StackPane where
     *  - The lowest layer on the stack is the Application Border pane passed in.
     *  - Above application layer is a layer to display application alerts
     *  - Above alert layer is a layer to display popups.
     * @param stage the main application stage
     * @param root the root node to show in the scene
     * @return the main application container.
     */
    public Pane makeContainer(Stage stage, BorderPane root) {
        LocationContext mainLocationContext = new DefaultLocationContext(FENXLIB_MAIN_APPLICATION_PANE);
        AnchorPane mainPane = new AnchorPane();
        Fenxlib.register(mainLocationContext, mainPane);

        mainPane.getChildren().addAll(root);


        return mainPane;
    }
}
