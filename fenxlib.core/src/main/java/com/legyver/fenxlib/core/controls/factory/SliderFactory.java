package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.control.Slider;

/**
 * Factory to create sliders
 */
public class SliderFactory implements StyleableFactory<Slider> {
	private final Double min;
	private final Double max;
	private final Double value;

	/**
	 * Construct a factory to make sliders with the specified min, max and default values
	 * @param min the minimum value
	 * @param max the maximum value
	 * @param value the default value
	 */
	public SliderFactory(Double min, Double max, Double value) {
		this.min = min;
		this.max = max;
		this.value = value;
	}

	/**
	 * Construct a factory to make sliders with unspecified min, max and default values
	 */
	public SliderFactory() {
		this(null, null, null);
	}

	/**
	 * Make the slider and register it with the application context.
	 * Note: it is only registered as type because there is no text to decorate the context with.
	 * @param locationContext the location context to register the widget as
	 * @return the new slider
	 */
	@Override
	public Slider makeNode(LocationContext locationContext) {
		Slider slider = makeSlider();
		if (min != null) {
			slider.setMin(min);
		}
		if (max != null) {
			slider.setMax(max);
		}
		if (value != null) {
			slider.setValue(value);
		}
		ApplicationContext.getComponentRegistry().register(locationContext, slider, true);
		return slider;
	}

	/**
	 * Factory to method to make the default javafx slider.
	 * Override this if you need something else.
	 * @return the slider
	 */
	protected Slider makeSlider() {
		return new Slider();
	}

}
