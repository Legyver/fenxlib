package com.legyver.fenxlib.core.layout;

/**
 * Specifications for the layout of a popup
 */
public class Target {
    private Double minWidth;
    private Double prefWidth;
    private Double maxWidth;
    private Double minHeight;
    private Double prefHeight;
    private Double maxHeight;

    private Double offsetX;
    private Double offsetY;
    private Alignment alignment = Alignment.CENTER;

    private Target() {
        //use the builder
    }

    /**
     * Get the minimum width
     * @return the minimum width
     */
    public Double getMinWidth() {
        return minWidth;
    }

    /**
     * Get the preferred width
     * @return the preferred width
     */
    public Double getPrefWidth() {
        return prefWidth;
    }

    /**
     * Get the maximum width
     * @return the maximum width
     */
    public Double getMaxWidth() {
        return maxWidth;
    }

    /**
     * Get the minimum height
     * @return the minimum height
     */
    public Double getMinHeight() {
        return minHeight;
    }

    /**
     * Get the preferred height
     * @return the preferred height
     */
    public Double getPrefHeight() {
        return prefHeight;
    }

    /**
     * Get the maximum height
     * @return the maximum height
     */
    public Double getMaxHeight() {
        return maxHeight;
    }

    /**
     * Get the X-axis offset for the popup
     * @return the X-axis offset
     */
    public Double getOffsetX() {
        return offsetX;
    }

    /**
     * Get the Y-axis offset for the popup
     * @return the Y-axis offset
     */
    public Double getOffsetY() {
        return offsetY;
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
        private final Target target = new Target();

        /**
         * Build the target region
         * @return the region specifications
         */
        public Target build() {
            return target;
        }

        /**
         * Specify a minimum width
         * @param minWidth the minimum width
         * @return this builder
         */
        public Builder minWidth(Double minWidth) {
            target.minWidth = minWidth;
            return this;
        }

        /**
         * Specify a preferred width
         * @param prefWidth the preferred width
         * @return this builder
         */
        public Builder prefWidth(Double prefWidth) {
            target.prefWidth = prefWidth;
            return this;
        }

        /**
         * Specify a maximum width
         * @param maxWidth the maximum width
         * @return this builder
         */
        public Builder maxWidth(Double maxWidth) {
            target.maxWidth = maxWidth;
            return this;
        }

        /**
         * Specify a minimum height
         * @param minHeight the minimum height
         * @return this builder
         */
        public Builder minHeight(Double minHeight) {
            target.minHeight = minHeight;
            return this;
        }

        /**
         * Specify a preferred height
         * @param prefHeight the preferred height
         * @return this builder
         */
        public Builder prefHeight(Double prefHeight) {
            target.prefHeight = prefHeight;
            return this;
        }

        /**
         * Specify a maximum height
         * @param maxHeight the maximum height
         * @return this builder
         */
        public Builder maxHeight(Double maxHeight) {
            target.maxHeight = maxHeight;
            return this;
        }

        /**
         * Specify the X-axis offset for the popup
         * @param offsetX the x-axis offset
         * @return this builder
         */
        public Builder offsetX(Double offsetX) {
            target.offsetX = offsetX;
            return this;
        }

        /**
         * Specify the Y-axis offset for the popup
         * @param offsetY Y-axis offset for the popup
         * @return this builder
         */
        public Builder offsetY(Double offsetY) {
            target.offsetY = offsetY;
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
