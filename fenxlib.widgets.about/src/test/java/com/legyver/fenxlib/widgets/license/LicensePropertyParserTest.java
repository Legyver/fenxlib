package com.legyver.fenxlib.widgets.license;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;


public class LicensePropertyParserTest {

    @Test
    public void parseV2Properties() throws Exception {
        InputStream inputStream = LicensePropertyParserTest.class.getResourceAsStream("v2-license.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        LicensePropertyParser licensePropertyParser = new LicensePropertyParser(properties);
        List<DependencyData> result = licensePropertyParser.parseItems();
        assertThat(result.size()).isEqualTo(1);

        DependencyData dependencyData = result.get(0);
        assertThat(dependencyData.getName()).isEqualTo("IcoMoon");
        assertThat(dependencyData.getRetrieved()).isEqualTo("2022-04-16");

        assertThat(dependencyData.getChange(0).getText()).isEqualTo("No change.");
        assertThat(dependencyData.getDisclaimer(0).getText()).isEqualTo("Not a real disclaimer.");

        assertThat(dependencyData.getAuthor(0).getText()).isEqualTo("IcoMoon");
        assertThat(dependencyData.getAuthor(0).getLink()).isEqualTo("https://icomoon.io/");

        assertThat(dependencyData.getTitle(0).getText()).isEqualTo("IcoMoon - Free");
        assertThat(dependencyData.getTitle(0).getLink()).isEqualTo("https://icomoon.io/#icons-icomoon");

        assertThat(dependencyData.getCopyright(0).getText()).isEqualTo("GPL");
        assertThat(dependencyData.getCopyright(0).getLink()).isEqualTo("http://www.gnu.org/licenses/gpl.html");

        assertThat(dependencyData.getCopyright(1).getText()).isEqualTo("CC BY 4.0");
        assertThat(dependencyData.getCopyright(1).getLink()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
    }

    @Test
    public void parseV1Properties() throws Exception {
        InputStream inputStream = LicensePropertyParserTest.class.getResourceAsStream("v1-license.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        LicensePropertyParser licensePropertyParser = new LicensePropertyParser(properties);
        List<DependencyData> result = licensePropertyParser.parseItems();
        assertThat(result.size()).isEqualTo(3);

        assertV1(result.get(0));
        assertV1(result.get(1));
        assertV1(result.get(2));
    }

    private void assertV1(DependencyData dependencyData) {
        if ("commons-lang3".equals(dependencyData.getName())) {
            assertCommonsLang(dependencyData);
        } else if ("com.legyver.utils".equals(dependencyData.getName())) {
            assertLegyverUtils(dependencyData);
        } else {
            assertIcoMoon(dependencyData);
        }
    }

    private void assertIcoMoon(DependencyData dependencyData) {
        assertThat(dependencyData.getName()).isEqualTo("io.icomoon");

        assertThat(dependencyData.getCopyright(0).getText()).isEqualTo("GPL");
        assertThat(dependencyData.getCopyright(0).getLink()).isEqualTo("http://www.gnu.org/licenses/gpl.html");

        assertThat(dependencyData.getCopyright(1).getText()).isEqualTo("CC BY 4.0");
        assertThat(dependencyData.getCopyright(1).getLink()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
    }

    private void assertLegyverUtils(DependencyData dependencyData) {
        assertThat(dependencyData.getName()).isEqualTo("com.legyver.utils");
        assertThat(dependencyData.getCopyright(0).getText()).isEqualTo("Apache License 2.0");
        assertThat(dependencyData.getCopyright(0).getLink()).isEqualTo("https://github.com/Legyver/utils/blob/master/LICENSE");
    }

    private void assertCommonsLang(DependencyData dependencyData) {
        assertThat(dependencyData.getName()).isEqualTo("commons-lang3");
        assertThat(dependencyData.getCopyright(0).getText()).isEqualTo("Apache License 2.0");
        assertThat(dependencyData.getCopyright(0).getLink()).isEqualTo("https://github.com/apache/commons-lang/blob/master/LICENSE.txt");
    }
}
