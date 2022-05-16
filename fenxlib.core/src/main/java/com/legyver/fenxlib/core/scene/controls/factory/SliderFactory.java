package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Slider;

/**
 * Factory to create sliders
 */
public class SliderFactory implements StyleableFactory<Slider> {
	/**
	 * Param for passing the min value to the constructor
	 */
	public static final String CONSTRUCTOR_PARAM_MIN = "min";
	/**
	 * Param for passing the max value to the constructor
	 */
	public static final String CONSTRUCTOR_PARAM_MAX= "max";
	/**
	 * Param for passing the initial value to the constructor
	 */
	public static final String CONSTRUCTOR_PARAM_INITIAL = "initial";
	private final Double min;
	private final Double max;
	private final Double value;

	/**
	 * Construct a factory to make sliders with the specified min, max and default values
	 * @param min the minimum value
	 * @param max the maximum value
	 * @param initial the initial value
	 */
	public SliderFactory(Double min, Double max, Double initial) {
		this.min = min;
		this.max = max;
		this.value = initial;
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
