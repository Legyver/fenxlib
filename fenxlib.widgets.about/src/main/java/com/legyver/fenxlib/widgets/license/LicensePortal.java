package com.legyver.fenxlib.widgets.license;

import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.ArrayList;
import java.util.List;

/**
 * Portal to display library metadata of the dependencies.
 * Only one is displayed at a time.  The rest are hidden.
 */
public class LicensePortal extends Control {
    private final List<DependencyView> licenses = new ArrayList<>();
    private DependencyView currentlyActivated;

    /**
     * Add dependency data
     * @param dependencyView the dependency data
     */
    public void addLicense(DependencyView dependencyView) {
        if (licenses.isEmpty()) {
            currentlyActivated = dependencyView;
            currentlyActivated.setVisible(true);
        } else {
            Platform.runLater(() -> currentlyActivated.setVisible(false));
        }
        this.licenses.add(dependencyView);
    }

    /**
     * Activate the associated dependency view
     * @param activate the index of the view to activate
     */
    public void activate(int activate) {
        currentlyActivated.setVisible(false);
        currentlyActivated = licenses.get(activate);
        currentlyActivated.setVisible(true);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LicensePortalSkin(this);
    }

    /**
     * Get the dependencies
     * @return the dependencies
     */
    public List<DependencyView> getLicenses() {
        return licenses;
    }
}
