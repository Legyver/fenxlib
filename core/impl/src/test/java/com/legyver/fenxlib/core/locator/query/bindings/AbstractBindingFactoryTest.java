package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.locator.query.QueryableComponentRegistry;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AbstractBindingFactoryTest extends ApplicationTest {
	private final TestBindingFactory factory = new TestBindingFactory();

	@Test
	public void bindTextField() throws Exception {
		QueryableComponentRegistry registry = ApplicationContext.getComponentRegistry();

		StringProperty s = new SimpleStringProperty();
		s.setValue("Test Value");
		TextField textField = new TextField();
		LocationContext locationContext = new DefaultLocationContext("Test panel");
		registry.register(locationContext, textField);

		ComponentQuery.RegionQueryBuilder query = new ComponentQuery.QueryBuilder(registry)
				.inRegion("Test panel");
		factory.bindTextField(s, query, null);
		assertThat(textField.getText(), is("Test Value"));
		assertThat(s.get(), is("Test Value"));

		textField.setText("New Value");
		assertThat(textField.getText(), is("New Value"));
		assertThat(s.get(), is("New Value"));
	}

	@Test
	public void bindDatePicker() throws Exception {
		QueryableComponentRegistry registry = ApplicationContext.getComponentRegistry();
		ObjectProperty<LocalDate> ld = new SimpleObjectProperty();
		ld.set(LocalDate.now());
		DatePicker picker = new DatePicker();
		LocationContext locationContext = new DefaultLocationContext("Test panel");
		registry.register(locationContext, picker);

		ComponentQuery.RegionQueryBuilder query = new ComponentQuery.QueryBuilder(registry)
				.inRegion("Test panel");
		factory.bindDatePicker(ld, query, null);
		assertThat(picker.getValue(), is(LocalDate.now()));
		assertThat(ld.get(), is(LocalDate.now()));

		picker.setValue(LocalDate.MIN);
		assertThat(picker.getValue(), is(LocalDate.MIN));
		assertThat(ld.get(), is(LocalDate.MIN));
	}



	private class TestBindingFactory implements TextFieldBindingFactory, DatePickerBindingFactory {

	}
}
