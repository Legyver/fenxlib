package com.legyver.fenxlib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GsonExploratoryTest {
	private static final String CONFIG_LAST_OPENED = 
			"{\n" +
			"  \"lastOpened\": {\n" +
			"    \"lastDirectory\": \"/temp/tmpDir\"\n" +
			"  }\n" +
			"}";
	
	private static final String CONFIG_RECENTLY_MODIFIED = 
			"{\r\n" +
			"    \"recentlyModified\" : {\r\n" +
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
			"    }\r\n" +
			"}";
	
	private static final String CONFIG_EXTENDED_MAP = 
			"{\n" +
			"  \"lastOpened\": {\n" +
			"    \"lastDirectory\": \"/temp/tmp\"\n" +
			"  },\n" +
			" \"extendedKey\": \"extended value\"\n" +
			"}";
	
	@Test
	public void mapLastOpenedStringToObject() {
		Type type = new TypeToken<Config>(){}.getType();
		Config result = (Config) new Gson().fromJson(CONFIG_LAST_OPENED, type);
		assertNotNull(result);
		assertNotNull(result.lastOpened);
		assertThat(result.lastOpened.lastDirectory, is("/temp/tmpDir"));
	}
	
	@Test
	public void mapRecentlyModifiedStringToObject() {
		Type type = new TypeToken<Config>(){}.getType();
		Config result = (Config) new Gson().fromJson(CONFIG_RECENTLY_MODIFIED, type);
		assertNotNull(result);
		assertNotNull(result.recentlyModified);
		assertThat(result.recentlyModified.limit, is(5));
	}
	
	@Test
	public void mapExtendedMapStringToObject() {
		Type type = new TypeToken<Config>(){}.getType();
		Config result = (Config) new Gson().fromJson(CONFIG_EXTENDED_MAP, type);
		assertNotNull(result);
//		assertNotNull(result.extended);
//		assertThat(result.extended.get("extendedKey"), is("extended value"));
	}
	
	@Test
	public void util() {
//		Type type = new TypeToken<Config>(){}.getType();
		Config config = new Config();
		config.lastOpened = new LastOpened();
		config.lastOpened.lastDirectory = "/temp/tmp";
		String s = new GsonBuilder().setPrettyPrinting().create().toJson(config);
		boolean breakHere = true;
	}
	
	static class ConfigMap extends HashMap<String, Map> {
		
	}
	
	static class Config {
		LastOpened lastOpened;
		RecentlyModified recentlyModified;
		Map<String, Map> extendedSections;
				
	}
	
	static class LastOpened {
		String lastDirectory;
	}
	
	static class RecentlyModified {
		int limit = 5;
		List<RecentlyViewedFile> values = new ArrayList<>();
	}
	
	static class RecentlyViewedFile {
		LocalDateTime lastAccessed; 
		String name;
		String path;
	}
}


