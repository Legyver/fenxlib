package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.TitledPaneOptions;
import com.legyver.fenxlib.core.util.LocationContextOperator;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Factory to create a TitledPane
 *
 */
public class TitledPaneFactory<T extends Pane> implements StyleableFactory<TitledPane, TitledPaneOptions> {

	/**
	 * Make a TitledPane.
	 * The name of the titled pane is passed down to any child factories so child elements can be located within this titled pane.
	 * @param locationContext the parent location context
	 * @return the TitledPane
	 * @throws CoreException if there is an Exception raised by the content factory
	 */
	@Override
	public TitledPane makeNode(LocationContext locationContext, TitledPaneOptions options) throws CoreException {
		LocationContext decorated = locationContext.decorateWith(options.getName());
		Node content = options.getContent();
		if (content != null) {
			if (content instanceof Pane) {
				Region spacer = new Region();
				spacer.setMinSize(200, 10);
				((Pane) content).getChildren().add(spacer);
			}
			new LocationContextOperator(content).reRegister(decorated, "titledpane_" + options.getName() + "_content");
		}

		TitledPane titledPane = makeTitledPane();
		titledPane.setText(options.getText());
		titledPane.setContent(options.getContent());
		Fenxlib.register(decorated, titledPane);
		return titledPane;
	}

	@Override
	public TitledPaneOptions newOptions() {
		return new TitledPaneOptions();
	}

	/**
	 * Factory method to create a new TitledPane.
	 * By default, returns the plain javafx {@link TitledPane}.  Override if you want something else
	 * @return the titled pane.
	 */
	protected TitledPane makeTitledPane() {
		return new TitledPane();
	}
}
