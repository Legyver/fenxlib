package com.legyver.fenxlib.tests.base.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.config.json.ConfigServiceImpl;
import com.legyver.fenxlib.config.json.util.JsonFileIOUtil;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Default test ConfigService.
 * If you need a custom test config, extend this and override the {@link #getTestResource(String)} method.
 * Additionally, if you don't need something custom, the config will just be instantiated and then ditched
 * at the end of the test cycle
 */
@SuppressWarnings("unchecked")
public abstract class TestConfigService extends ConfigServiceImpl<TestConfig> {
    private TestFileIOUtil fileIOUtil = new TestFileIOUtil();

    @Override
    public TestConfig loadConfig(String filename) throws CoreException {
        try (InputStream inputStream = getTestResource(filename)) {
            if (inputStream != null) {
                String string = IOUtils.toString(new InputStreamReader(inputStream));
                return (TestConfig) fileIOUtil.readModel(string);
            }
            return null;
        } catch (IOException ex) {
            throw new CoreException("Unable to read resource: " + filename, ex);
        }
    }

    @Override
    public boolean saveConfig(String filename, TestConfig config) throws CoreException {
        return true;//no saving in test
    }

    @Override
    public int order() {
        return 5;//before the file-based order (10), but also allow for others to usurp it
    }

    @Override
    public void init(ApplicationConfigInstantiator initializer) {
        fileIOUtil = new TestFileIOUtil(initializer);
    }

    /**
     * Get the input stream for a test resource.
     * Check out the TestConfigServiceImpl in fenxlib.tests.base for an example
     * This is primarily designed to support bringing your own test config
     * @param filename the filename of the config
     * @return the input stream for the test config.
     */
    protected abstract InputStream getTestResource(String filename);

    /**
     * Test FileIO Util based on JSON application configs.
     * There is no output functionality.
     */
    protected class TestFileIOUtil extends JsonFileIOUtil {

        /**
         * Construct a File IO Util with given initializer
         * @param initializer the initializer to use when an application config is not specified
         */
        public TestFileIOUtil(ApplicationConfigInstantiator initializer) {
            super(initializer);
        }

        /**
         * Construct a File IO Util with the default initializer specified in the JsonFileIOUtil
         */
        public TestFileIOUtil() {
            super();
        }

        @Override
        protected void writeFileFromString(File file, String string) throws IOException {
            //noop not writing files in tests
        }
    }
}
