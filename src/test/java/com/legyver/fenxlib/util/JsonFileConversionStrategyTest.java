package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.parts.LastOpened;
import com.legyver.fenxlib.config.parts.RecentlyViewedFile;
import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class JsonFileConversionStrategyTest {
	GsonFileConversionStrategy strategy = new GsonFileConversionStrategy();
	private static final String CONFIG_EMPTY =
			"{}";
	private static final String CONFIG_LAST_OPENED = 
			"{\n" +
			"  \"lastOpened\": {\n" +
			"    \"lastDirectory\": \"/temp/tmpDir\"\n" +
			"  }\n" +
			"}";
	private static final String CONFIG_RECENTLY_MODIFIED = 
			"{\r\n" +
			"  \"sections\" : {\r\n" +
			"    \"recentModifed\" : {\r\n" +
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
			"  }\r\n" +
			"}";
	
//	@Test
//	public void noSectionsFromModel() throws Exception {
//		TestConfig config = new TestConfig();
//		assertThat(config.getSections().size(), is(0));
////		String value = strategy.fromModel(config, new GsonFileContext(null));
//		assertThat(value, is(CONFIG_EMPTY));
//	}
//	
//	@Test
//	public void noSectionsToModel() throws Exception {
//		TestConfig config = (TestConfig) strategy.toModel(CONFIG_EMPTY, new GsonFileContext(null));
//		assertNotNull(config);
//		assertThat(config.getSections().size(), is(0));
//	}
//	
//	@Test
//	public void oneSectionFromModel() throws Exception {
//		TestConfig config = new TestConfig();
//		LastOpened opened = new LastOpened();
//		opened.setLastDirectory("/temp/tmpDir");
//		config.addSection(LastOpened.NAME, opened);
//		assertThat(config.getSections().size(), is(1));
//		String value = strategy.fromModel(config, new GsonFileContext(null));
//		assertThat(value, is(CONFIG_LAST_OPENED));
//	}
//	
//	@Test
//	public void oneSectionToModel() throws Exception {
//		Map<String, Map> config = (Map<String, Map>) strategy.toModel(CONFIG_LAST_OPENED, new GsonFileContext(null));
//		assertNotNull(config);
//		assertThat(config.values().size(), is(1));
//		Map<String, Map> opened = config.get(LastOpened.NAME);
////		LastOpened opened = (LastOpened) config.getSection(LastOpened.NAME);
//		assertNotNull(opened);
//		assertThat(opened.get("lastDirectory"), is("/temp/tmpDir"));
////		assertThat(opened.getLastDirectory(), is("/temp/tmpDir"));
//		
//	}
	
//	@Test
//	public void nestedSectionFromModel() throws Exception {			
//		TestConfig config = new TestConfig();
//		RecentlyModified recentlyModified = new RecentlyModified();
//		LocalDateTime base = LocalDateTime.of(LocalDate.of(2018, 7, 14), LocalTime.of(17, 10, 57));
//		
//		recentlyModified.addValue(mockRecentlyViewed(base, "Name 1", "/temp/tmp/Name 1.ext"));
//		recentlyModified.addValue(mockRecentlyViewed(base.minusHours(4), "Name 2", "/temp/tmp/Name 2.ext"));
//		config.addSection(RecentlyModified.NAME, recentlyModified);
//		
//		String value = strategy.fromModel(config, new JsonFileContext(null, new TypeReference<TestConfig>(){}));
//		assertThat(value, is(CONFIG_RECENTLY_MODIFIED));
//	}

	private RecentlyViewedFile mockRecentlyViewed(LocalDateTime accessed, String name, String path) {
		RecentlyViewedFile viewed = new RecentlyViewedFile();
		viewed.setLastAccessed(accessed);
		viewed.setName(name);
		viewed.setPath(path);
		return viewed;
	}
	
}
