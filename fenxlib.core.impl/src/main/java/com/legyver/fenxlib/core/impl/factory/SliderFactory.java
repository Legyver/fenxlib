package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.context.BaseApplicationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.Slider;

/**
 * Factory to create sliders
 */
public class SliderFactory implements NodeFactory<Slider> {
	private final Double min;
	private final Double max;
	private final Double value;

	/**
	 * Construct a factory to make sliders with the specified min, max and default value
	 * @param min the minimum value
	 * @param max the maximum value
	 * @param value the default value
	 */
	public SliderFactory(Double min, Double max, Double value) {
		this.min = min;
		this.max = max;
		this.value = value;
	}

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
		Slider slider;
		if (min == null || max == null || value == null) {
			slider = new Slider();
		} else {
			slider = new Slider(min, max, value);
		}
		BaseApplicationContext.getComponentRegistry().register(locationContext, slider, true);
		return slider;
	}
}
