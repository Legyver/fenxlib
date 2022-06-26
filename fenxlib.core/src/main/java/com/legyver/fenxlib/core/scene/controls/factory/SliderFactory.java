package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.SliderOptions;
import javafx.scene.control.Slider;

/**
 * Factory to create sliders
 */
public class SliderFactory implements StyleableFactory<Slider, SliderOptions> {

	/**
	 * Make the slider and register it with the application context.
	 * Note: it is only registered as type because there is no text to decorate the context with.
	 * @param locationContext the location context to register the widget as
	 * @return the new slider
	 */
	@Override
	public Slider makeNode(LocationContext locationContext, SliderOptions options) {
		Slider slider = makeSlider();
		options.minAdapter().setNotNull((d) -> slider.setMin(d));
		options.maxAdapter().setNotNull((d) -> slider.setMax(d));
		options.valueAdapter().setNotNull((d) -> slider.setValue(d));
		ApplicationContext.getComponentRegistry().register(locationContext.decorateWith(options.getName()), slider, true);
		return slider;
	}

	@Override
	public SliderOptions newOptions() {
		return new SliderOptions();
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
