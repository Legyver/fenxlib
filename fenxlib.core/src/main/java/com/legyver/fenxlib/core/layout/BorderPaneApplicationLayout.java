package com.legyver.fenxlib.core.layout;

import com.legyver.fenxlib.core.layout.options.*;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.util.function.Consumer;

/**
 * Application layout comprised of the following regions that correspond roughly to a {@link javafx.scene.layout.BorderPane}
 * - Top region
 * - Bottom region
 * - Left region
 * - Right region
 * - Center region
 * If using the {@link BorderPaneBuilder}, the left, right and bottom regions can support a control that can hide/show the region.
 */
public class BorderPaneApplicationLayout extends BaseApplicationLayout {

    /**
     * Construct an application layout in a border pane
     * @param mainPane the border pane to layout the application in
     */
    public BorderPaneApplicationLayout(BorderPane mainPane) {
        super(mainPane);
    }

    /**
     * Builder for specifying a BorderPane-style application layout.
     * If an option is not specified, that region of the BorderPane is set to null.
     * If a control is not specified for hiding-showing a region, a default one is provided that always shows the region.
     */
    public static class BorderPaneBuilder extends BaseBuilder<BorderPaneBuilder, BorderPaneApplicationLayout> {
        private ITopRegionOptions topRegionOptions;
        private ILeftRegionOptions leftRegionOptions;
        private IRegionControl leftRegionControlOptions = new AlwaysShowRegionControlOptions();
        private ICenterRegionOptions centerRegionOptions;
        private IRightRegionOptions rightRegionOptions;
        private IRegionControl rightRegionControlOptions = new AlwaysShowRegionControlOptions();
        private IBottomRegionOptions bottomRegionOptions;
        private IRegionControl bottomRegionControlOptions = new AlwaysShowRegionControlOptions();

        @Override
        public BorderPaneApplicationLayout buildInternal() {
            BorderPane borderPane = new BorderPane();
            if (topRegionOptions != null) {
                borderPane.setTop(topRegionOptions.getContent());
            }
            if (centerRegionOptions != null) {
                borderPane.setCenter(centerRegionOptions.getContent());
            }
            RegionControl regionControl = new RegionControl();
            regionControl.setupControl(rightRegionControlOptions, rightRegionOptions, content -> borderPane.setRight(content));
            regionControl.setupControl(leftRegionControlOptions, leftRegionOptions, content -> borderPane.setLeft(content));
            regionControl.setupControl(bottomRegionControlOptions, bottomRegionOptions, content -> borderPane.setBottom(content));

            BorderPaneApplicationLayout layout = new BorderPaneApplicationLayout(borderPane);
            return layout;
        }

        /**
         * Specify the top region of an application
         * @param topRegionOptions top region of an application
         * @return this builder
         */
        public BorderPaneBuilder topRegionOptions(ITopRegionOptions topRegionOptions) {
            this.topRegionOptions = topRegionOptions;
            return this;
        }

        /**
         * Specify the left region of an application
         * @param leftRegionOptions the left region options
         * @return this builder
         */
        public BorderPaneBuilder leftRegionOptions(ILeftRegionOptions leftRegionOptions) {
            this.leftRegionOptions = leftRegionOptions;
            return this;
        }

        /**
         * Specify the control to hide/show the left region of an application
         * @param leftRegionControlOptions the controls for showing/hiding the left region
         * @return this builder
         */
        public BorderPaneBuilder leftRegionControlOptions(IRegionControl leftRegionControlOptions) {
            this.leftRegionControlOptions = leftRegionControlOptions;
            return this;
        }

        /**
         * Specify the center region of the application
         * @param centerRegionOptions the center region
         * @return this builder
         */
        public BorderPaneBuilder centerRegionOptions(ICenterRegionOptions centerRegionOptions) {
            this.centerRegionOptions = centerRegionOptions;
            return this;
        }

        /**
         * Specify right region of the application
         * @param rightRegionOptions the right region
         * @return this builder
         */
        public BorderPaneBuilder rightRegionOptions(IRightRegionOptions rightRegionOptions) {
            this.rightRegionOptions = rightRegionOptions;
            return this;
        }

        /**
         * Specify the control to hide/show the right region of the application
         * @param rightRegionControlOptions the right region control
         * @return this builder
         */
        public BorderPaneBuilder rightRegionControlOptions(IRegionControl rightRegionControlOptions) {
            this.rightRegionControlOptions = rightRegionControlOptions;
            return this;
        }

        /**
         * Specify the bottom region of the application
         * @param bottomRegionOptions the bottom region of the application
         * @return thois builder
         */
        public BorderPaneBuilder bottomRegionOptions(IBottomRegionOptions bottomRegionOptions) {
            this.bottomRegionOptions = bottomRegionOptions;
            return this;
        }

        /**
         * Specify the control to show/hide the bottom region of the application
         * @param bottomRegionControlOptions the bottom region control
         * @return this builder
         */
        public BorderPaneBuilder bottomRegionControlOptions(IRegionControl bottomRegionControlOptions) {
            this.bottomRegionControlOptions = bottomRegionControlOptions;
            return this;
        }
    }

    private static class RegionControl {

        private void setupControl(IRegionControl control, IRegionOptions options, Consumer<Node> setter) {
            control.showRegionProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue && options != null) {
                    setter.accept(options.getContent());
                } else {
                    setter.accept(null);
                }
            });
            if (control.isShowRegion() && options != null) {
                setter.accept(options.getContent());
            }
        }
    }
}
