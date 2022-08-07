package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.context.ResourceScope;
import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.layout.IApplicationLayout;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.Locale;

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
     * The Fenxlib Popup Pane
     */
    public static final String FENXLIB_POPUP_PANE = "_FENXLIB_POPUP_PANE_";

    private final Stage stage;
    private final StageStyle stageStyle;
    private final ResourceScope resourceScope;

    /**
     * Construct a factory to create the scene
     * @param stage the stage for the scene
     * @param stageStyle the style for the scene.  if null, it will not be set
     * @param resourceScope the scope to apply stylesheets from
     */
    public SceneFactory(Stage stage, StageStyle stageStyle, ResourceScope resourceScope) {
        this.stage = stage;
        this.stageStyle = stageStyle;
        this.resourceScope = resourceScope;
    }

    /**
     * Construct a factory to create the scene
     * @param stage the stage for the scene
     */
    public SceneFactory(Stage stage) {
        this(stage, null, ResourceScope.APPLICATION);
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
        List<URL> stylesheetUrls = ApplicationContext.getStylesheetsForScope(resourceScope);
        if (stylesheetUrls == null) {
            //use the application ones
            stylesheetUrls = ApplicationContext.getStylesheets();
        }
        if (stylesheetUrls != null) {
            stylesheetUrls.stream().map(url -> url.toExternalForm())
                    .forEach(styleSheet -> scene.getStylesheets().add(styleSheet));
        }
        return scene;
    }

    /**
     * Set the scene and title properties on the stage.  This also registers the stage with the ApplicationContext
     * @param scene the scene
     * @param layout the layout
     */
    protected void initStage(Scene scene, T layout) {
        stage.setScene(scene);
        if (layout.titleProperty() != null) {
            //set initial value
            setI18NTitleOnStage(layout.getTitle());
            //update if it changes
            layout.titleProperty().addListener((observable, oldValue, newValue) -> {
                setI18NTitleOnStage(newValue);
            });
        }
        if (stageStyle != null) {
            stage.initStyle(stageStyle);
        }
        ApplicationContext.registerStage(stage);
    }

    private void setI18NTitleOnStage(String value) {
        String i18nMessage = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.getDefault(), value);
        stage.setTitle(i18nMessage);
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
        VBox mainPane = new VBox();

        Fenxlib.register(mainLocationContext, mainPane);

        MenuBar menuBar = layout.getMenuBar();
        Region applicationPane = layout.getMainPane();
        if (menuBar != null) {
            mainPane.getChildren().add(menuBar);
            double menuBarHeight = menuBar.getHeight();
            mainPane.heightProperty().addListener((observable, oldValue, newValue) -> {
                applicationPane.setPrefHeight(newValue.doubleValue() - menuBarHeight);
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
