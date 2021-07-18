package com.legyver.fenxlib.core.impl.locator.query;

import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryBuilderTest {

	@Test
	public void queryRegionPanelName() {
		ComponentQuery query = new ComponentQuery.QueryBuilder()
				.inRegion("region")
				.inSubRegion("panel")
				.named("name");
		assertThat(query.getQueryString()).isEqualTo("region::panel::name");
	}

	@Test
	public void queryRegionName() {
		ComponentQuery query = new ComponentQuery.QueryBuilder()
				.inRegion("region")
				.named("name");
		assertThat(query.getQueryString()).isEqualTo("region::name");
	}
}
