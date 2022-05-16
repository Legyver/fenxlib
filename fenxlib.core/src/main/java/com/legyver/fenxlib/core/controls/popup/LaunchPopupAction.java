package com.legyver.fenxlib.core.controls.popup;

import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import com.legyver.fenxlib.core.layout.Target;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Action to launch a popup
 */
public class LaunchPopupAction implements EventHandler<ActionEvent> {
    private final Popup popup;
    private final ComponentQuery anchorComponentQuery;
    private final Target target;

    /**
     * Construct an action to launch a Popup window
     * @param popup the popup to show
     * @param anchorComponentQuery the query identifying the component to display the popup over
     * @param target target specifications
     */
    public LaunchPopupAction(Popup popup, ComponentQuery anchorComponentQuery, Target target) {
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
                .only(),
                new Target.Builder()
                        .prefWidth(200.0)
                        .prefHeight(100.0)
                        .centered()
                        .build()
        );
    }

    @Override
    public void handle(ActionEvent event) {
        Stage stage = Fenxlib.getPrimaryStage();
        Pane pane = (Pane) anchorComponentQuery.execute().get();
        Bounds bounds = pane.getLayoutBounds();

        Double leftX = null;
        Double upperY = null;
        Double lowerY = null;

        if (target.getAlignment() == Target.Alignment.LEFT) {
            leftX = bounds.getMinX();
            lowerY = bounds.getMinY();
        } else if (target.getAlignment() == Target.Alignment.RIGHT) {
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
