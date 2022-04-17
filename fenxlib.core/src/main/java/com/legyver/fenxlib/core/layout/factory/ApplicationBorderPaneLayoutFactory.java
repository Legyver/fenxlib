package com.legyver.fenxlib.core.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.layout.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.layout.options.RegionInitializationOptions;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

/**
 * In olden-days this was just called BorderPaneFactory.  Since this is really specific to application layout, renaming it
 */
public class ApplicationBorderPaneLayoutFactory {
    private FactoryAction centerFactory;
    private FactoryAction topFactory;
    private FactoryAction bottomFactory;
    private FactoryAction leftFactory;
    private FactoryAction rightFactory;

    /**
     * Construct a BorderPane factory with factories for the center, top, bottom, and left and right sides
     * @param options the options containing the options for each section of the border pane
     */
    public ApplicationBorderPaneLayoutFactory(BorderPaneInitializationOptions options) {
        this.centerFactory = new FactoryAction(options.getCenterOptions(), (pane, region) -> pane.setCenter(region));
        this.topFactory = new FactoryAction(options.getTopOptions(), (pane, region) -> pane.setTop(region));
        this.bottomFactory = new FactoryAction(options.getBottomOptions(), (pane, region) -> pane.setBottom(region));
        this.leftFactory = new FactoryAction(options.getLeftOptions(), (pane, region) -> pane.setLeft(region));
        this.rightFactory = new FactoryAction(options.getRightOptions(), (pane, region) -> pane.setRight(region));
    }


    /**
     * Create the border pane and apply the nested factories to create its five regions
     * @return the border pane
     * @throws CoreException if there is an error during construction
     */
    public BorderPane makeBorderPaneWithContent() throws CoreException {
        BorderPane borderPane = makeBorderPane();
        unwrap(() -> Stream.of(centerFactory, topFactory, leftFactory, rightFactory, bottomFactory)//order matters, since bottomFactory calls getLeft()/getRight()
                .forEach(f -> wrap(() -> f.execute(borderPane))));
        return borderPane;
    }

    /**
     * Factory method to construct a new BorderPane.
     * Creates the javafx BorderPane.  Override if you need something else.
     * @return the border pane
     */
    protected BorderPane makeBorderPane() {
        return new BorderPane();
    }

    private class FactoryAction {

        private final RegionInitializationOptions options;
        private final BiConsumer<BorderPane, Region> setter;

        FactoryAction(RegionInitializationOptions options, BiConsumer<BorderPane, Region> setter) {
            this.options = options;
            this.setter = setter;
        }

        void execute(BorderPane borderPane) throws CoreException {
            if (options != null && options.getFactory() != null) {
                Region region = options.getFactory().makeRegion(borderPane, options);
                setter.accept(borderPane, region);
            }
        }
    }
}
