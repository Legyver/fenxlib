package com.legyver.fenxlib.widgets.license;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenSourceReferenceListTest extends ApplicationTest {

	public static final String APACHE_LICENSE_2_0 = "Apache License 2.0";

	@Test
	public void parseLicenseProperties() throws Exception {
		Properties properties = new Properties();
		properties.load(OpenSourceReferenceListTest.class.getResourceAsStream("license.properties"));
		OpenSourceReferenceList openSourceReferenceList = new OpenSourceReferenceList(properties);
		List<OpenSourceReferenceList.Item> items = openSourceReferenceList.getItems();
		assertThat(items.size()).isEqualTo(6);
		Iterator<OpenSourceReferenceList.Item> itemIterator = items.iterator();
		assertItem(itemIterator.next(), "com.fasterxml.jackson.core.jackson-databind", APACHE_LICENSE_2_0, "https://github.com/FasterXML/jackson-databind/blob/master/LICENSE");
		assertItem(itemIterator.next(), "commons-codec", APACHE_LICENSE_2_0, "https://github.com/apache/commons-codec/blob/master/LICENSE.txt");
		assertItem(itemIterator.next(), "commons-io", APACHE_LICENSE_2_0, "https://github.com/apache/commons-io/blob/master/LICENSE.txt");
		assertItem(itemIterator.next(), "io.icomoon", "GPL", "CC BY 4.0", "http://www.gnu.org/licenses/gpl.html", "http://creativecommons.org/licenses/by/4.0/");
		assertItem(itemIterator.next(), "org.apache.logging.log4j", APACHE_LICENSE_2_0, "https://logging.apache.org/log4j/2.0/license.html");
	}

	private void assertItem(OpenSourceReferenceList.Item item, String library, String license, String licenseLink) {
		assertThat(item.getArtifact()).isEqualTo(library);
		assertThat(item.getLicenseNames()).containsExactlyInAnyOrder(license);
		assertThat(item.getLicenseLinks()).containsExactlyInAnyOrder(licenseLink);
	}

	private void assertItem(OpenSourceReferenceList.Item item, String library, String license1, String license2, String licenseLink1, String licenseLInk2) {
		assertThat(item.getArtifact()).isEqualTo(library);
		assertThat(item.getLicenseNames()).containsExactlyInAnyOrder(license1, license2);
		assertThat(item.getLicenseLinks()).containsExactlyInAnyOrder(licenseLink1, licenseLInk2);
	}
}
