package com.legyver.fenxlib.core.api.config;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderedServiceTest {

	@Test
	public void orderServices() {
		List<TestService> services = Arrays.asList(new TestService1(), new TestService2());
		assertThat(services.get(0).order()).isEqualTo(10);
		assertThat(services.get(1).order()).isEqualTo(1);
		Collections.sort(services);
		assertThat(services.get(0).order()).isEqualTo(1);
		assertThat(services.get(1).order()).isEqualTo(10);
	}

	private interface TestService extends OrderedService<TestService> {

	}

	private static class TestService1 implements TestService {

		@Override
		public int order() {
			return 10;
		}
	}


	private static class TestService2 implements TestService {

		@Override
		public int order() {
			return 1;
		}
	}
}
