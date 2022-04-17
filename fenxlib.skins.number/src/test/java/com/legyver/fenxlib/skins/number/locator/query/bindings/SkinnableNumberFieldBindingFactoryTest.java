package com.legyver.fenxlib.skins.number.locator.query.bindings;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SkinnableNumberFieldBindingFactoryTest extends ApplicationTest {

	@Test
	public void bindMoneyField() throws Exception {
		QueryableComponentRegistry registry = ApplicationContext.getComponentRegistry();

		ObjectProperty<BigDecimal> money = new SimpleObjectProperty<>();
		money.set(BigDecimal.valueOf(1000));
		SkinnableNumberField numberField = new SkinnableNumberField("test-css");
		LocationContext locationContext = new DefaultLocationContext("Test panel");
		registry.register(locationContext, numberField);

		IRegionDiscriminator query = new ComponentQuery.QueryBuilder()
				.inRegion("Test panel");
		SkinnableNumberFieldBinding.bindTextField(money, query, null);
		assertThat(numberField.getValue().doubleValue()).isEqualTo(1000.0);
		assertThat(money.get().doubleValue()). isEqualTo(1000.0);

		numberField.setValue(BigDecimal.valueOf(2000));
		assertThat(numberField.getValue().doubleValue()).isEqualTo(2000.0);
		assertThat(money.get().doubleValue()).isEqualTo(2000.0);
	}
}
