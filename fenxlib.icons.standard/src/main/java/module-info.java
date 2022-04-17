/**
 * Standard icons for the fenxlib library based on the Free IcoMoon package
 */
module fenxlib.icons.standard {
    requires com.legyver.core;
    requires com.legyver.fenxlib.core;

    exports com.legyver.fenxlib.icons.standard;

    provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.icons.standard.license.LicenseServiceImpl;
    provides com.legyver.fenxlib.core.icons.service.IconLoaderService with com.legyver.fenxlib.icons.standard.service.IconLoaderServiceImpl;
}