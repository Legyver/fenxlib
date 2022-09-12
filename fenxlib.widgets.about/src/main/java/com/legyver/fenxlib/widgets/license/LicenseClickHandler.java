package com.legyver.fenxlib.widgets.license;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Click handler to display the license detail for the selected library
 */
public class LicenseClickHandler implements EventHandler<MouseEvent> {
    private final LicensePortal licensePortal;
    private final int index;

    /**
     * Construct a click handler to display the license detail for the selected library
     * @param licensePortal the portal to display the license data in
     * @param index the index corresponding to this license data
     */
    public LicenseClickHandler(LicensePortal licensePortal, int index) {
        this.licensePortal = licensePortal;
        this.index = index;
    }

    @Override
    public void handle(MouseEvent event) {
        licensePortal.activate(index);
    }
}
