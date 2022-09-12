package com.legyver.fenxlib.widgets.license;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;

/**
 * Skin for a {@link LicensePortal}.
 *
 */
public class LicensePortalSkin extends SkinBase<LicensePortal> {
    private final StackPane stackPane;

    /**
     * Construct a skin for a {@link LicensePortal}.
     * @param licensePortal the license portal to skin
     */
    public LicensePortalSkin(LicensePortal licensePortal) {
        super(licensePortal);
        stackPane = new StackPane();
        boolean first = true;
        for (DependencyView dependencyView : licensePortal.getLicenses()) {
            stackPane.getChildren().add(dependencyView);
            dependencyView.setVisible(first);
            first = false;
        }

        getChildren().add(stackPane);
    }
}
