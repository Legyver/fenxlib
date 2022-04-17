package com.legyver.fenxlib.core.controls.popup;

import com.legyver.fenxlib.core.util.GuidAwareListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

/**
 * Skin for {@link AlertPane}
 */
public class AlertPaneSkin extends SkinBase<AlertPane> {
    private SplitPane triSplit;
    private FlowPane topLeft;
    private FlowPane topRight;
    private FlowPane bottomLeft;
    private FlowPane bottomRight;

    /**
     * Construct a skin for the {@link AlertPane}
     * @param alertPane the alert to skin
     */
    public AlertPaneSkin(AlertPane alertPane) {
        super(alertPane);

        topLeft = new FlowPane();
        topLeft.setAlignment(Pos.TOP_LEFT);
        alertPane.getTopLeftAlerts().addListener(new GuidAwareListChangeListener<>(topLeft.getChildren()));

        topRight = new FlowPane();
        topRight.setAlignment(Pos.TOP_RIGHT);
        alertPane.getTopRightAlerts().addListener(new GuidAwareListChangeListener<>(topRight.getChildren()));

        bottomLeft = new FlowPane();
        bottomLeft.setAlignment(Pos.BOTTOM_LEFT);
        alertPane.getBottomLeftAlerts().addListener(new GuidAwareListChangeListener<>(bottomLeft.getChildren()));

        bottomRight = new FlowPane();
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);
        alertPane.getBottomRightAlerts().addListener(new GuidAwareListChangeListener<>(bottomRight.getChildren()));

        AnchorPane leftAnchor = new AnchorPane(topLeft, bottomLeft);
        AnchorPane rightAnchor = new AnchorPane(topRight, bottomRight);

        triSplit = new SplitPane(leftAnchor, new Region(), rightAnchor);
        triSplit.setDividerPosition(0, 0.22);
        triSplit.setDividerPosition(1, 0.78);

        getChildren().add(triSplit);
    }
}
