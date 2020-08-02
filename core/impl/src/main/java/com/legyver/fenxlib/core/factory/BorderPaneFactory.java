package com.legyver.fenxlib.core.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.factory.options.RegionInitializationOptions;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class BorderPaneFactory {
	private FactoryAction centerFactory;
	private FactoryAction topFactory;
	private FactoryAction bottomFactory;
	private FactoryAction leftFactory;
	private FactoryAction rightFactory;

	public BorderPaneFactory(BorderPaneInitializationOptions options) {
		this.centerFactory = new FactoryAction(options.getCenterOptions(), (pane, region) -> pane.setCenter(region));
		this.topFactory = new FactoryAction(options.getTopOptions(), (pane, region) -> pane.setTop(region));
		this.bottomFactory = new FactoryAction(options.getBottomOptions(), (pane, region) -> pane.setBottom(region));
		this.leftFactory = new FactoryAction(options.getLeftOptions(), (pane, region) -> pane.setLeft(region));
		this.rightFactory = new FactoryAction(options.getRightOptions(), (pane, region) -> pane.setRight(region));
	}

	public BorderPane makeBorderPane() throws CoreException {
		BorderPane borderPane = new BorderPane();
		unwrap(() -> Stream.of(centerFactory, topFactory, leftFactory, rightFactory, bottomFactory)//order matters, since bottomFactory calls getLeft()/getRight()
				.forEach(f -> wrap(() -> f.execute(borderPane))));
		return borderPane;
	}

	private class FactoryAction {

		private final RegionInitializationOptions options;
		private final BiConsumer<BorderPane, Region> setter;

		public FactoryAction(RegionInitializationOptions options, BiConsumer<BorderPane, Region> setter) {
			this.options = options;
			this.setter = setter;
		}

		public void execute(BorderPane borderPane) throws CoreException {
			if (options != null && options.getFactory() != null) {
				Region region = options.getFactory().makeRegion(borderPane, options);
				setter.accept(borderPane, region);
			}
		}
	}


}
