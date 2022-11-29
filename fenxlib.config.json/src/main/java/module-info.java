import com.legyver.fenxlib.config.json.JsonConfigService;

/**
 * Handle the loading/saving of application config files for applications that use a JSON config
 */
module com.legyver.fenxlib.config.json {
    exports com.legyver.fenxlib.config.json;
    //legyver dependencies
    requires com.legyver.fenxlib.api;
    requires com.legyver.core;
    requires com.legyver.utils.adaptex;
    requires com.legyver.utils.jsonmigration;
    requires com.legyver.utils.mapqua;
    requires com.legyver.utils.ruffles;
    //third party dependencies
    requires org.apache.commons.io;
    requires org.apache.commons.lang3;
    requires org.apache.logging.log4j;

    provides com.legyver.fenxlib.api.config.ConfigService with JsonConfigService;
}