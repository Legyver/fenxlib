package com.legyver.fenxlib.core.layout;

import com.legyver.fenxlib.api.layout.IApplicationLayout;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;

/**
 * Base class for an Application Layout.  Just contains a menu bar.
 */
public abstract class BaseApplicationLayout implements IApplicationLayout {
    /**
     * Main pane for the application
     */
    private final Pane mainPane;
    /**
     * Menu bar for the application
     */
    private MenuBar menuBar;
    /**
     * Bindable title property for the application for the application title bar
     */
    private StringProperty title;
    /**
     * Default height of the application
     */
    private double height;
    /**
     * Default width of the application
     */
    private double width;

    /**
     * Construct a base application layout with a main pane
     * @param mainPane the main application pane content
     */
    protected BaseApplicationLayout(Pane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public MenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public String getTitle() {
        return title.get();
    }

    @Override
    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Set the title of the application
     * @param title the title to use
     */
    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    /**
     * Get the main application pane
     * @return the main application pane
     */
    @Override
    public Pane getMainPane() {
        return mainPane;
    }


    /**
     * Base builder for specifying an application layout
     * @param <B> the type of the subclassing builder
     * @param <L> the type of the layout to produce
     */
    public abstract static class BaseBuilder<B extends BaseBuilder, L extends BaseApplicationLayout> {
        private MenuBar menuBar;
        private StringProperty titleProperty = new SimpleStringProperty("Fenxlib App");
        private double height = 600;
        private double width = 800;

        /**
         * Build the application layout and set the menu bar on it
         * @return the application layout
         */
        public final L build() {
            BaseApplicationLayout built = buildInternal();
            built.menuBar = menuBar;
            built.title = titleProperty;
            built.height = height;
            built.width = width;
            return (L) built;
        }

        /**
         * Internal build method
         * @return the layout as produced by this builder
         */
        protected abstract L buildInternal();

        /**
         * Add a menu bar
         * @param menuBar the menu bar to add
         * @return this builder
         */
        public B menuBar(MenuBar menuBar) {
            this.menuBar = menuBar;
            return (B) this;
        }

        /**
         * Specify the title for the application.
         * @param title the title to use
         * @return this builder.
         */
        public B title(String title) {
            this.titleProperty.set(title);
            return (B) this;
        }

        /**
         * Specify the SpringProperty to bind the title in the application title bar to.
         * Note: if you have already set the title using {@link #title(String)}, you may need to do so again as this replaces the string property the value is set on.
         * @param titleProperty the property to bind the title to
         * @return this builder
         */
        public B title(StringProperty titleProperty) {
            this.titleProperty = titleProperty;
            return (B) this;
        }

        /**
         * Specify the default height for the application
         * @param height the default height
         * @return this height
         */
        public B height(double height) {
            this.height = height;
            return (B) this;
        }

        /**
         * Specify the default width for the application
         * @param width the default width
         * @return this builder
         */
        public B width(double width) {
            this.width = width;
            return (B) this;
        }
    }
}
