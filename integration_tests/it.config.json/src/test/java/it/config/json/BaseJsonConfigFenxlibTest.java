package it.config.json;

import com.legyver.fenxlib.tests.base.FenxlibTest;

/**
 * Base test for fenxlib tests that need to specify an application config in json
 */
public class BaseJsonConfigFenxlibTest extends FenxlibTest {

    @Override
    protected Class<JsonTestConfig> getConfigClass() {
        return JsonTestConfig.class;
    }
}
