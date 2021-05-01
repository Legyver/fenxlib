package com.legyver.fenxlib.skins.number.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.api.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SkinnableNumberFieldBindingFactoryTest extends ApplicationTest {
	private final TestBindingFactory factory = new TestBindingFactory();

	@Test
	public void bindMoneyField() throws Exception {
		QueryableComponentRegistry registry = ApplicationContext.getComponentRegistry();

		ObjectProperty<BigDecimal> money = new SimpleObjectProperty();
		money.set(BigDecimal.valueOf(1000));
		SkinnableNumberField numberField = new SkinnableNumberField("test-css");
		LocationContext locationContext = new DefaultLocationContext("Test panel");
		registry.register(locationContext, numberField);

		IRegionDiscriminator query = new ComponentQuery.QueryBuilder()
				.inRegion("Test panel");
		factory.bindTextField(money, query, null);
		assertThat(numberField.getValue().doubleValue(), is(1000.0));
		assertThat(money.get().doubleValue(), is(1000.0));

		numberField.setValue(BigDecimal.valueOf(2000));
		assertThat(numberField.getValue().doubleValue(), is(2000.0));
		assertThat(money.get().doubleValue(), is(2000.0));
	}

	private class TestBindingFactory implements SkinnableNumberFieldBindingFactory {

	}
}
