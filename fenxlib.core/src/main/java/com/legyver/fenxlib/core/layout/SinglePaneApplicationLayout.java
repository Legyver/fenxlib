package com.legyver.fenxlib.core.layout;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Application with only a single application pane
 */
public class SinglePaneApplicationLayout extends BaseApplicationLayout {
    /**
     * Construct an application layout with a single main pane
     *
     * @param mainPane the main application pane content
     */
    public SinglePaneApplicationLayout(Pane mainPane) {
        super(mainPane);
    }

    /**
     * Builder for constructing an application layout with a single pane
     */
    public static class SinglePaneApplicationLayoutBuilder extends BaseBuilder<SinglePaneApplicationLayoutBuilder, SinglePaneApplicationLayout> {
        private Pane pane = new AnchorPane();

        @Override
        protected SinglePaneApplicationLayout buildInternal() {
            return new SinglePaneApplicationLayout(pane);
        }

        /**
         * Specify a pane to be used as the main application pane
         * @param pane the pain to use
         * @return this builder
         */
        public SinglePaneApplicationLayoutBuilder pane(Pane pane) {
            this.pane = pane;
            return this;
        }
    }
}
