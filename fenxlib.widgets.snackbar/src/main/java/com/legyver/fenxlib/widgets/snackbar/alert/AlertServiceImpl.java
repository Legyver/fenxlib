package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.alert.*;
import com.legyver.fenxlib.api.context.ResourceScope;
import com.legyver.fenxlib.api.layout.anchor.AlertAnchor;
import com.legyver.fenxlib.api.layout.anchor.PopupAnchor;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextChainBuilder;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Optional;

/**
 * Default AlertService.  Displays alerts in a {@link com.legyver.fenxlib.widgets.snackbar.Snackbar} by default.
 */
public class AlertServiceImpl implements AlertService {
    private static final Logger logger = LogManager.getLogger(AlertServiceImpl.class);
    private static final String ALERTS_REGION = "__alerts__";

    private AlertFactory<? extends IAlert> alertFactory;
    private final EnumMap<Level, IAlert.TargetRegion> levelTargetRegions = new EnumMap<>(Level.class);

    @Override
    public void defaultLevelAlertLocations(EnumMap<Level, IAlert.TargetRegion> targetRegionEnumMap) {
        targetRegionEnumMap.entrySet().stream().forEach(entry -> {
            Level level = entry.getKey();
            IAlert.TargetRegion targetRegion = entry.getValue();
            if (targetRegion != null) {
                levelTargetRegions.put(level, targetRegion);
            }
        });
    }

    @Override
    public void displayAlert(AlertTextContent alertTextContent, Level level, Long timeout) {
       if (alertFactory == null) {
            alertFactory = AlertFactoryRegistry.getInstance().getFactoryFromClasspath();
        }
        IAlert alert = alertFactory.makeAlert(alertTextContent, level, timeout);

        try {
            IAlert.TargetRegion targetRegion = alert.getTargetRegion();
            if (targetRegion == null) {
                targetRegion = levelTargetRegions.get(level);
            }
            if (targetRegion != null) {
                String targetRegionName = targetRegion.name();

                Optional<VirtualAlertFlow> foundVirtualAlertFlow = new ComponentQuery.QueryBuilder()
                        .inRegion(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE)
                        .inSubRegion(ALERTS_REGION)
                        .named(targetRegionName)
                        .execute();
                VirtualAlertFlow virtualAlertFlow;
                if (foundVirtualAlertFlow.isPresent()) {
                    virtualAlertFlow = foundVirtualAlertFlow.get();
                } else {
                    virtualAlertFlow = initVirtualAlertFlow(targetRegion, targetRegionName);
                }
                if (virtualAlertFlow != null) {
                    AlertLayout alertLayout = new AlertLayout((Control) alert);
                    Stage stage = new Stage();
                    Scene scene = new SceneFactory<>(stage, StageStyle.UNDECORATED, ResourceScope.ALERTS).makeScene(alertLayout);
                    virtualAlertFlow.addAlert(alert, stage);
                    stage.setAlwaysOnTop(true);
                    stage.showAndWait();
                }
            }
        } catch (CoreException e) {
            logger.error("Error querying alert", e);
        }
    }

    private VirtualAlertFlow initVirtualAlertFlow(IAlert.TargetRegion targetRegion, String targetRegionName) {
        VirtualAlertFlow virtualAlertFlow = null;
        VirtualAlertFlow.AlertFlowDirection alertFlowDirection;
        switch (targetRegion) {
            case APPLICATION_TOP_LEFT:
            case APPLICATION_TOP_RIGHT:
                alertFlowDirection = VirtualAlertFlow.AlertFlowDirection.TOP_DOWN;
                break;
            case APPLICATION_BOTTOM_LEFT:
            case APPLICATION_BOTTOM_RIGHT:
                alertFlowDirection = VirtualAlertFlow.AlertFlowDirection.BOTTOM_UP;
                break;
            default:
                throw new RuntimeException("Unsupported target region : " + targetRegion);
        }
        AlertAnchor alertAnchor = targetRegion.getAlertAnchor();
        if (alertAnchor != null) {
            if (alertAnchor instanceof PopupAnchor) {
                virtualAlertFlow = new VirtualAlertFlow(alertFlowDirection, (PopupAnchor) alertAnchor);
                LocationContext locationContext = new LocationContextChainBuilder()
                        .in(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE)
                        .in(ALERTS_REGION)
                        .named(targetRegionName)
                        .build();

                Fenxlib.register(locationContext, virtualAlertFlow);
            }
        }
        return virtualAlertFlow;
    }

    @Override
    public int order() {
        return 0;
    }
}
