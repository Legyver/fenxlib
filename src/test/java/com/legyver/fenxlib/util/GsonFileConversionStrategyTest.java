package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.parts.LastOpened;
import com.legyver.fenxlib.util.GsonFileContext.ModelInstantiator;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GsonFileConversionStrategyTest {
	private final GsonFileConversionStrategy strategy = new GsonFileConversionStrategy();
	private static final String DATA_LAST_OPENED = "  \"lastOpened\": {\n" +
			"    \"lastDirectory\": \"/temp/tmpDir\"\n" +
			"  }";
	private static final String CONFIG_LAST_OPENED = 
			"{\n" +
			DATA_LAST_OPENED +
			"\n}";
	private static final String DATA_RECENTLY_MODIFIED = "    \"recentlyModified\" : {\r\n" +
			"      \"limit\" : 5,\r\n" +
			"      \"values\" : [ {\r\n" +
			"        \"lastAccessed\" : {\r\n" +
			"          \"month\" : \"JULY\",\r\n" +
			"          \"year\" : 2018,\r\n" +
			"          \"dayOfMonth\" : 14,\r\n" +
			"          \"dayOfWeek\" : \"SATURDAY\",\r\n" +
			"          \"dayOfYear\" : 195,\r\n" +
			"          \"hour\" : 17,\r\n" +
			"          \"minute\" : 10,\r\n" +
			"          \"monthValue\" : 7,\r\n" +
			"          \"nano\" : 0,\r\n" +
			"          \"second\" : 57,\r\n" +
			"          \"chronology\" : {\r\n" +
			"            \"id\" : \"ISO\",\r\n" +
			"            \"calendarType\" : \"iso8601\"\r\n" +
			"          }\r\n" +
			"        },\r\n" +
			"        \"name\" : \"Name 1\",\r\n" +
			"        \"path\" : \"/temp/tmp/Name 1.ext\"\r\n" +
			"      }, {\r\n" +
			"        \"lastAccessed\" : {\r\n" +
			"          \"month\" : \"JULY\",\r\n" +
			"          \"year\" : 2018,\r\n" +
			"          \"dayOfMonth\" : 14,\r\n" +
			"          \"dayOfWeek\" : \"SATURDAY\",\r\n" +
			"          \"dayOfYear\" : 195,\r\n" +
			"          \"hour\" : 13,\r\n" +
			"          \"minute\" : 10,\r\n" +
			"          \"monthValue\" : 7,\r\n" +
			"          \"nano\" : 0,\r\n" +
			"          \"second\" : 57,\r\n" +
			"          \"chronology\" : {\r\n" +
			"            \"id\" : \"ISO\",\r\n" +
			"            \"calendarType\" : \"iso8601\"\r\n" +
			"          }\r\n" +
			"        },\r\n" +
			"        \"name\" : \"Name 2\",\r\n" +
			"        \"path\" : \"/temp/tmp/Name 2.ext\"\r\n" +
			"      } ]\r\n" +
			"    }\r\n";
	private static final String CONFIG_RECENTLY_MODIFIED = 
			"{\r\n" +
		 DATA_RECENTLY_MODIFIED +
			"}";
	
	@Test
	public void marshallUnchanged() throws Exception {
		String expected = "{\r\n" + DATA_LAST_OPENED + ",\n" + DATA_RECENTLY_MODIFIED + "}";
		ForwardCompatibleModel model = strategy.toModel(CONFIG_LAST_OPENED, new GsonFileContext(null, () -> new TestConfig()));
		assertNotNull(model);
		TestConfig config = (TestConfig) model;
		assertNotNull(config.getLastOpened());
		LastOpened lastOpened = config.getLastOpened();
		assertThat(lastOpened.getLastDirectory(), is("/temp/tmpDir"));
		String unmarshalled = strategy.fromModel(model, new GsonFileContext(null, () -> new TestConfig()));
		assertThat(unmarshalled, is(expected));
		
	}
}
