package com.legyver.fenxlib.widgets.snackbar.alert;

import javafx.scene.control.SkinBase;

/**
 * Skin for an alert flow.  Note: nothing is actually displayed in this skin since the alerts exist on their own stages.
 */
public class VirtualAlertFlowSkin extends SkinBase<VirtualAlertFlow> {

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected VirtualAlertFlowSkin(VirtualAlertFlow control) {
        super(control);
        //nothing is actually displayed in the control
    }
}
