import com.legyver.fenxlib.config.json.JsonConfigService;

/**
 * Handle the loading/saving of application config files for applications that use a JSON config
 */
module com.legyver.fenxlib.config.json {
    exports com.legyver.fenxlib.config.json;
    exports com.legyver.fenxlib.config.json.util;
    //legyver dependencies
    requires com.legyver.fenxlib.api;
    requires com.legyver.core;
    requires com.legyver.utils.adaptex;
    requires com.legyver.utils.mapqua;
    //third party dependencies
    requires org.apache.commons.io;

    provides com.legyver.fenxlib.api.config.ConfigService with JsonConfigService;
}