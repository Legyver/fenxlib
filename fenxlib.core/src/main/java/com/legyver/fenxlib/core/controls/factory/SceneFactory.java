package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.layout.IApplicationLayout;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.stream.Stream;

/**
 * Factory to create a Fenxlib Application Scene.
 * See {@link #makeContainer(IApplicationLayout)} for customization
 */
public class SceneFactory<T extends IApplicationLayout> {
    private static final Logger logger = LogManager.getLogger(SceneFactory.class);

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
    /**
     * Construct a factory to create the scene with the appropriate size/width and load any stylesheets
     * @param stage the stage for the scene
     * @param stylesheetUrls any resources to be loaded that need to be incorporated into the scene
     */
    public SceneFactory(Stage stage, URL... stylesheetUrls) {
        this.stylesheetUrls = stylesheetUrls;
        this.stage = stage;
    }

    /**
     * Make the scene
     * @param layout the main application layout
     * @return the scene
     */
    public Scene makeScene(T layout) {
        Pane decorator = makeContainer(layout);
        Scene scene = new Scene(decorator, layout.getWidth(), layout.getHeight());
        initStage(scene, layout);
        if (stylesheetUrls != null) {
            Stream.of(stylesheetUrls).map(url -> url.toExternalForm())
                    .forEach(styleSheet -> scene.getStylesheets().add(styleSheet));
        }
        return scene;
    }

    /**
     * Set the scene and title properties on the stage
     * @param scene the scene
     * @param layout the layout
     */
    protected void initStage(Scene scene, T layout) {
        stage.setScene(scene);
        stage.titleProperty().bind(layout.titleProperty());
    }

    /**
     * Hook to provide a custom decorator for the main screen.
     * By default, returns a StackPane where
     *  - The lowest layer on the stack is the Application Border pane passed in.
     *  - Above application layer is a layer to display application alerts
     *  - Above alert layer is a layer to display popups.
     * @param layout the root node to show in the scene
     * @return the main application container.
     */
    protected Pane makeContainer(T layout) {
        LocationContext mainLocationContext = new DefaultLocationContext(FENXLIB_MAIN_APPLICATION_PANE);
        AnchorPane mainPane = new AnchorPane();
        Fenxlib.register(mainLocationContext, mainPane);

        MenuBar menuBar = layout.getMenuBar();
        Pane applicationPane = layout.getMainPane();
        if (menuBar != null) {
            mainPane.getChildren().add(menuBar);
            double menuBarHeight = menuBar.getHeight();
            mainPane.heightProperty().addListener((observable, oldValue, newValue) -> {
                applicationPane.setMaxHeight(newValue.doubleValue() - menuBarHeight);
            });
            applicationPane.setPrefHeight(mainPane.getHeight());
        }
        if (applicationPane != null) {
            mainPane.getChildren().add(applicationPane);
        } else {
            logger.warn("No main application pane supplied");
        }
        return mainPane;
    }
}
