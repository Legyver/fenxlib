package com.legyver.fenxlib.core.version;

import com.legyver.fenxlib.api.version.AbstractFenxlibModuleVersionService;

import java.io.IOException;
import java.util.Properties;

/**
 * Load the version data for this module
 */
public class ModuleVersionServiceImpl extends AbstractFenxlibModuleVersionService {
    @Override
    protected void loadProperties(Properties properties) throws IOException {
        properties.load(ModuleVersionServiceImpl.class.getResourceAsStream("version.properties"));
    }

}
