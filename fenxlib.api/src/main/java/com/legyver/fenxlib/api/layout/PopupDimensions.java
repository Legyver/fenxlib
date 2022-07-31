package com.legyver.fenxlib.api.layout;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Specifications for the layout of a popup
 */
public class PopupDimensions {
    /**
     * The minimum width of the popup
     */
    private final DoubleProperty minWidth = new SimpleDoubleProperty();
    /**
     * The preferred width of the popup
     */
    private final DoubleProperty prefWidth = new SimpleDoubleProperty();
    /**
     * The maximum width of the popup
     */
    private final DoubleProperty maxWidth = new SimpleDoubleProperty();
    /**
     * The minimum height of the popup
     */
    private final DoubleProperty minHeight = new SimpleDoubleProperty();
    /**
     * The preferred height of the popup
     */
    private final DoubleProperty prefHeight = new SimpleDoubleProperty();
    /**
     * The maximum height of the popup
     */
    private final DoubleProperty maxHeight = new SimpleDoubleProperty();
    /**
     * The x-offset of the popup
     */
    private DoubleProperty offsetX = new SimpleDoubleProperty();
    /**
     * The y-offset of the popup
     */
    private DoubleProperty offsetY = new SimpleDoubleProperty();

    private Alignment alignment = Alignment.CENTER;

    private PopupDimensions() {
        //use the builder
    }

    /**
     * Get the minimum width
     * @return the minimum width
     */
    public Double getMinWidth() {
        return minWidth.get();
    }

    /**
     * Get the minimum width property
     * @return the minimum width property
     */
    public DoubleProperty minWidthProperty() {
        return minWidth;
    }

    /**
     * set the minimum width
     * @param minWidth the minimum width
     */
    public void setMinWidth(double minWidth) {
        this.minWidth.set(minWidth);
    }

    /**
     * Get the preferred width
     * @return the preferred width
     */
    public Double getPrefWidth() {
        return prefWidth.get();
    }

    /**
     * Get the preferred width property
     * @return the preferred width property
     */
    public DoubleProperty prefWidthProperty() {
        return prefWidth;
    }

    /**
     * Set the preferred width
     * @param prefWidth the preferred width
     */
    public void setPrefWidth(double prefWidth) {
        this.prefWidth.set(prefWidth);
    }

    /**
     * Get the maximum width
     * @return the maximum width
     */
    public Double getMaxWidth() {
        return maxWidth.get();
    }

    /**
     * Get the maximum width property
     * @return the maximum width property
     */
    public DoubleProperty maxWidthProperty() {
        return maxWidth;
    }

    /**
     * Set the maximum width
     * @param maxWidth the maximum width
     */
    public void setMaxWidth(double maxWidth) {
        this.maxWidth.set(maxWidth);
    }

    /**
     * Get the minimum height
     * @return the minimum height
     */
    public Double getMinHeight() {
        return minHeight.get();
    }

    public DoubleProperty minHeightProperty() {
        return minHeight;
    }

    /**
     * Set the minimum height
     * @param minHeight the minimum height
     */
    public void setMinHeight(double minHeight) {
        this.minHeight.set(minHeight);
    }

    /**
     * Get the preferred height
     * @return the preferred height
     */
    public Double getPrefHeight() {
        return prefHeight.get();
    }

    /**
     * Get the preferred height property
     * @return the preferred height property
     */
    public DoubleProperty prefHeightProperty() {
        return prefHeight;
    }

    /**
     * Set the preferred height
     * @param prefHeight the preferred height
     */
    public void setPrefHeight(double prefHeight) {
        this.prefHeight.set(prefHeight);
    }

    /**
     * Get the maximum height
     * @return the maximum height
     */
    public Double getMaxHeight() {
        return maxHeight.get();
    }

    /**
     * Get the preferred width property
     * @return the preferred width property
     */
    public DoubleProperty maxHeightProperty() {
        return maxHeight;
    }

    /**
     * Set the maximum height
     * @param maxHeight the maximum height
     */
    public void setMaxHeight(double maxHeight) {
        this.maxHeight.set(maxHeight);
    }

    /**
     * Get the X-axis offset for the popup
     * @return the X-axis offset
     */
    public Double getOffsetX() {
        return offsetX.get();
    }

    /**
     * Get the offset width property
     * @return the offset width property
     */
    public DoubleProperty offsetXProperty() {
        return offsetX;
    }

    /**
     * Set the x-offset
     * @param offsetX the x-offset
     */
    public void setOffsetX(double offsetX) {
        this.offsetX.set(offsetX);
    }

    /**
     * Get the Y-axis offset for the popup
     * @return the Y-axis offset
     */
    public Double getOffsetY() {
        return offsetY.get();
    }

    /**
     * Get the offset height property
     * @return the offset height property
     */
    public DoubleProperty offsetYProperty() {
        return offsetY;
    }

    /**
     * Set the y-offset
     * @param offsetY the y-offset
     */
    public void setOffsetY(double offsetY) {
        this.offsetY.set(offsetY);
    }

    /**
     * Get the alignment of the popup within its parent reference bounds
     * @return the alignment of the popup
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     * Builder to construct the location options for a floating popup window
     */
    public static class Builder {
        private final PopupDimensions target = new PopupDimensions();

        /**
         * Build the target region
         * @return the region specifications
         */
        public PopupDimensions build() {
            return target;
        }

        /**
         * Specify a minimum width
         * @param minWidth the minimum width
         * @return this builder
         */
        public Builder minWidth(Double minWidth) {
            target.minWidth.set(minWidth);
            return this;
        }

        /**
         * Specify a preferred width
         * @param prefWidth the preferred width
         * @return this builder
         */
        public Builder prefWidth(Double prefWidth) {
            target.prefWidth.set(prefWidth);
            return this;
        }

        /**
         * Specify a maximum width
         * @param maxWidth the maximum width
         * @return this builder
         */
        public Builder maxWidth(Double maxWidth) {
            target.maxWidth.set(maxWidth);
            return this;
        }

        /**
         * Specify a minimum height
         * @param minHeight the minimum height
         * @return this builder
         */
        public Builder minHeight(Double minHeight) {
            target.minHeight.set(minHeight);
            return this;
        }

        /**
         * Specify a preferred height
         * @param prefHeight the preferred height
         * @return this builder
         */
        public Builder prefHeight(Double prefHeight) {
            target.prefHeight.set(prefHeight);
            return this;
        }

        /**
         * Specify a maximum height
         * @param maxHeight the maximum height
         * @return this builder
         */
        public Builder maxHeight(Double maxHeight) {
            target.maxHeight.set(maxHeight);
            return this;
        }

        /**
         * Specify the X-axis offset for the popup
         * @param offsetX the x-axis offset
         * @return this builder
         */
        public Builder offsetX(Double offsetX) {
            target.offsetX.set(offsetX);
            return this;
        }

        /**
         * Specify the Y-axis offset for the popup
         * @param offsetY Y-axis offset for the popup
         * @return this builder
         */
        public Builder offsetY(Double offsetY) {
            target.offsetY.set(offsetY);
            return this;
        }

        /**
         * Specify the alignment for the popup with respect to its anchor position
         * @param alignment the alignment
         * @return this builder
         */
        public Builder alignment(Alignment alignment) {
            target.alignment = alignment;
            return this;
        }

        /**
         * Right align the popup with respect to the parent position reference
         * @return this builder
         */
        public Builder rightAligned() {
            return alignment(Alignment.RIGHT);
        }

        /**
         * Left align the popup with respect to the parent position reference
         * @return this builder
         */
        public Builder leftAligned() {
            return alignment(Alignment.LEFT);
        }

        /**
         * Center the popup with respect to the parent position reference
         * @return this builder
         */
        public Builder centered() {
            return alignment(Alignment.CENTER);
        }
    }

    /**
     * Alignment of a popup with respect to the parent window
     */
    public enum Alignment {
        /**
         * Left alignment.
         * In general, popups that are left-aligned will be over the left side of the screen
         */
        LEFT,
        /**
         * Right alignment
         * In general, popups that are right-aligned will be over the right side of the screen
         */
        RIGHT,
        /**
         * Center alignment
         * In general, popups that are center aligned will be displayed over the center of the screen
         */
        CENTER
    }
}
