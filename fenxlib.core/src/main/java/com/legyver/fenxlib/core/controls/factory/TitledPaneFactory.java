package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Factory to create a TitledPane
 *
 */
public class TitledPaneFactory<T extends Pane> implements StyleableFactory<TitledPane> {
	/**
	 * Constructor parameter to indicate the title of the pane
	 */
	public static final String TITLE = "title";
	/**
	 * Constructor parameter to specify the content of the pane
	 */
	public static final String CONTENT = "content";

	private final String title;
	private final TitledPaneContentFactory<T> contentFactory;
	private final T content;

	/**
	 * Construct a TitledPaneFactory with a given title and contentFactory
	 * @param title the title of the TitledPane
	 * @param contentFactory content factory that will create the content of the TitledPane
	 * @deprecated Use {@link com.legyver.fenxlib.core.controls.ControlsFactory#make(Class, java.util.Map)}
	 * This ultimately designates to {@link TitledPaneFactory(String, T)}   
	 */
	@Deprecated
	public TitledPaneFactory(String title, TitledPaneContentFactory<T> contentFactory) {
		this.title = title;
		this.contentFactory = contentFactory;
		this.content = null;
	}

	/**
	 * Construct a factory to create a TitledPane with the specified title and content.
	 * @param title the title of the pane
	 * @param content the content of the pane
	 */
	public TitledPaneFactory(String title, T content) {
		this.title = title;
		this.content = content;
		this.contentFactory = null;
	}

	/**
	 * Make a TitledPane.
	 * The name of the titled pane is passed down to any child factories so child elements can be located within this titled pane.
	 * @param locationContext the parent location context
	 * @return the TitledPane
	 * @throws CoreException if there is an Exception raised by the content factory
	 */
	@Override
	public TitledPane makeNode(LocationContext locationContext) throws CoreException {
		LocationContextDecorator decorated = new LocationContextDecorator(locationContext);
		decorated.setName(title);
		Pane content;
		if (this.content == null) {
			content = contentFactory.makeNode(decorated);
		} else {
			content = this.content;
		}
		Region spacer = new Region();
		spacer.setMinSize(200, 10);
		content.getChildren().add(spacer);

		TitledPane titledPane = makeTitledPane();
		titledPane.setText(title);
		titledPane.setContent(content);
		return titledPane;
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
