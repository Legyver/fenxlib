import com.legyver.fenxlib.api.controls.service.NodeInstantiationService;

/**
 * MaterialFX extension for fenxlib
 */
module com.legyver.fenxlib.extensions.materialfx {
    requires com.legyver.core;
    requires com.legyver.fenxlib.api;
    requires com.legyver.fenxlib.core;
    requires MaterialFX;

    exports com.legyver.fenxlib.extensions.materialfx.controls.factory;
    exports com.legyver.fenxlib.extensions.materialfx.controls.options;

    //reflection for option Mixins
    opens com.legyver.fenxlib.extensions.materialfx.controls.options to org.apache.commons.lang3;

    provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.extensions.materialfx.license.LicenseServiceImpl;
    provides NodeInstantiationService with com.legyver.fenxlib.extensions.materialfx.MaterialFXNodeInstantiationService;
}