package com.legyver.fenxlib.core.locator.query;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueryBuilderTest {

	@Test
	public void queryRegionPanelName() {
		ComponentQuery query = new ComponentQuery.QueryBuilder()
				.inRegion("region")
				.inSubRegion("panel")
				.named("name");
		assertThat(query.getQueryString(), is("region::panel::name"));
	}

	@Test
	public void queryRegionName() {
		ComponentQuery query = new ComponentQuery.QueryBuilder()
				.inRegion("region")
				.named("name");
		assertThat(query.getQueryString(), is("region::name"));
	}
}
