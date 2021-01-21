package com.legyver.fenxlib.widget.license;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OpenSourceReferenceListTest extends ApplicationTest {

	public static final String APACHE_LICENSE_2_0 = "Apache License 2.0";

	@Test
	public void parseLicenseProperties() throws Exception {
		Properties properties = new Properties();
		properties.load(OpenSourceReferenceListTest.class.getResourceAsStream("license.properties"));
		OpenSourceReferenceList openSourceReferenceList = new OpenSourceReferenceList(properties);
		List<OpenSourceReferenceList.Item> items = openSourceReferenceList.getItems();
		assertThat(items.size(), is(6));
		Iterator<OpenSourceReferenceList.Item> itemIterator = items.iterator();
		assertItem(itemIterator.next(), "com.fasterxml.jackson.core.jackson-databind", APACHE_LICENSE_2_0, "https://github.com/FasterXML/jackson-databind/blob/master/LICENSE");
		assertItem(itemIterator.next(), "com.jfoenix", APACHE_LICENSE_2_0, "https://github.com/jfoenixadmin/JFoenix/blob/master/LICENSE");
		assertItem(itemIterator.next(), "commons-codec", APACHE_LICENSE_2_0, "https://github.com/apache/commons-codec/blob/master/LICENSE.txt");
		assertItem(itemIterator.next(), "commons-io", APACHE_LICENSE_2_0, "https://github.com/apache/commons-io/blob/master/LICENSE.txt");
		assertItem(itemIterator.next(), "io.icomoon", "GPL", "CC BY 4.0", "http://www.gnu.org/licenses/gpl.html", "http://creativecommons.org/licenses/by/4.0/");
		assertItem(itemIterator.next(), "org.apache.logging.log4j", APACHE_LICENSE_2_0, "https://logging.apache.org/log4j/2.0/license.html");
	}

	private void assertItem(OpenSourceReferenceList.Item item, String library, String license, String licenseLink) {
		assertThat(item.getArtifact(), is(library));
		assertThat(item.getLicenseNames(), containsInAnyOrder(license));
		assertThat(item.getLicenseLinks(), containsInAnyOrder(licenseLink));
	}

	private void assertItem(OpenSourceReferenceList.Item item, String library, String license1, String license2, String licenseLink1, String licenseLInk2) {
		assertThat(item.getArtifact(), is(library));
		assertThat(item.getLicenseNames(), IsIterableContainingInOrder.contains(license1, license2));
		assertThat(item.getLicenseLinks(), IsIterableContainingInOrder.contains(licenseLink1, licenseLInk2));
	}
}
