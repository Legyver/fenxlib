package com.legyver.fenxlib.core.controls.popup;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import com.legyver.fenxlib.api.layout.PopupDimensions;
import com.legyver.fenxlib.core.menu.options.ShowPopupMenuOption;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Action to launch a popup
 * @deprecated Not sure if this is still the way forward, but maybe it just needs to be refactored and combined with {@link ShowPopupMenuOption}
 */
@Deprecated
public class LaunchPopupAction implements EventHandler<ActionEvent> {
    private static final Logger logger = LogManager.getLogger(LaunchPopupAction.class);

    private final Popup popup;
    private final ComponentQuery anchorComponentQuery;
    private final PopupDimensions target;

    /**
     * Construct an action to launch a Popup window
     * @param popup the popup to show
     * @param anchorComponentQuery the query identifying the component to display the popup over
     * @param target target specifications
     */
    public LaunchPopupAction(Popup popup, ComponentQuery anchorComponentQuery, PopupDimensions target) {
        this.anchorComponentQuery = anchorComponentQuery;
        this.target = target;
        this.popup = popup;
    }

    /**
     * If no specific anchor query is specified, position over the main application window
     * @param popup the popup to launch
     */
    public LaunchPopupAction(Popup popup) {
        this(popup, new ComponentQuery.QueryBuilder()
                .inRegion(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE)
                        .typed(StackPane.class)
                ,
                new PopupDimensions.Builder()
                        .prefWidth(200.0)
                        .prefHeight(100.0)
                        .centered()
                        .build()
        );
    }

    @Override
    public void handle(ActionEvent event) {
        Stage stage = Fenxlib.getPrimaryStage();
        Pane pane = null;
        try {
            pane = (Pane) anchorComponentQuery.execute().get();
        } catch (CoreException e) {
            logger.error("Unable to launch popup", e);
        }
        Bounds bounds = pane.getLayoutBounds();

        Double leftX = null;
        Double upperY = null;
        Double lowerY = null;

        if (target.getAlignment() == PopupDimensions.Alignment.LEFT) {
            leftX = bounds.getMinX();
            lowerY = bounds.getMinY();
        } else if (target.getAlignment() == PopupDimensions.Alignment.RIGHT) {
            leftX = bounds.getMaxX();
            lowerY = bounds.getMinY();
        } else {
            leftX = bounds.getCenterX();
            lowerY = bounds.getCenterY();
        }

        leftX = bounds.getCenterX();
        //adjust for width of thing
        if (target.getPrefWidth() != null) {
            leftX = leftX - target.getPrefWidth() * 0.5;
        } else if (target.getMinWidth() != null) {
            leftX = leftX - target.getMinWidth() * 0.5;
        } else if (target.getMaxWidth() != null) {
            leftX = leftX - target.getMaxWidth() * 0.5;
        }
        if (target.getOffsetX() != null) {
            leftX = leftX + target.getOffsetX();
        }

        //adjust for height
        if (target.getPrefWidth() != null) {
            upperY = upperY - target.getPrefWidth() * 0.5;
        } else if (target.getMinWidth() != null) {
            upperY = upperY - target.getMinWidth() * 0.5;
        } else if (target.getMaxWidth() != null) {
            upperY = upperY - target.getMaxWidth() * 0.5;
        }
        if (target.getOffsetY() != null) {
            upperY = upperY + target.getOffsetY();
        }

        Stage popupStage = new Stage(StageStyle.UNDECORATED);
        popupStage.setX(leftX);
        popupStage.setY(upperY);
        Scene scene = new Scene(popup);
        popupStage.setScene(scene);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();

    }
}
