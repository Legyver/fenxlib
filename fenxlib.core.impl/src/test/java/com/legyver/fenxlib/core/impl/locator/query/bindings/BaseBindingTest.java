package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.api.locator.query.QueryableComponentRegistry;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseBindingTest extends ApplicationTest {

	@Test
	public void bindTextField() throws Exception {
		QueryableComponentRegistry registry = ApplicationContext.getComponentRegistry();

		StringProperty s = new SimpleStringProperty();
		s.setValue("Test Value");
		TextField textField = new TextField();
		LocationContext locationContext = new DefaultLocationContext("Test panel");
		registry.register(locationContext, textField);

		IRegionDiscriminator query = new ComponentQuery.QueryBuilder()
				.inRegion("Test panel");
		TextFieldBinding.bindTextField(s, query, null);
		assertThat(textField.getText()).isEqualTo("Test Value");
		assertThat(s.get()).isEqualTo("Test Value");

		textField.setText("New Value");
		assertThat(textField.getText()).isEqualTo("New Value");
		assertThat(s.get()).isEqualTo("New Value");
	}

	@Test
	public void bindDatePicker() throws Exception {
		QueryableComponentRegistry registry = ApplicationContext.getComponentRegistry();
		ObjectProperty<LocalDate> ld = new SimpleObjectProperty();
		ld.set(LocalDate.now());
		DatePicker picker = new DatePicker();
		LocationContext locationContext = new DefaultLocationContext("Test panel");
		registry.register(locationContext, picker);

		IRegionDiscriminator query = new ComponentQuery.QueryBuilder()
				.inRegion("Test panel");
		DatePickerBinding.bindDatePicker(ld, query, null);
		assertThat(picker.getValue()).isEqualTo(LocalDate.now());
		assertThat(ld.get()).isEqualTo(LocalDate.now());

		picker.setValue(LocalDate.MIN);
		assertThat(picker.getValue()).isEqualTo(LocalDate.MIN);
		assertThat(ld.get()).isEqualTo(LocalDate.MIN);
	}
}
