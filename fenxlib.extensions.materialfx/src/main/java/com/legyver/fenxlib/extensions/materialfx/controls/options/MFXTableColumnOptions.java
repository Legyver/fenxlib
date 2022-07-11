package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.extensions.materialfx.controls.factory.MFXTableColumnFactory;
import io.github.palexdev.materialfx.controls.MFXTableColumn;

import java.util.Comparator;

/**
 * Options for a MFXTableColumn control
 */
public class MFXTableColumnOptions extends BaseControlBuilder<MFXTableColumnOptions> implements StyleableControlOptions<MFXTableColumn>,
        TextMixin<MFXTableColumnOptions> {
    private String text;
    private Boolean resizable;
    private Comparator comparator;

    /**
     * Specify if the stepper is resizable
     * @param resizable true if the stepper is resizable
     * @return this builder
     */
    public MFXTableColumnOptions resizable(boolean resizable) {
        this.resizable = resizable;
        return me();
    }

    /**
     * Get the resizable flag
     * @return the flag
     */
    public Boolean getResizable() {
        return resizable;
    }

    /**
     * Specify a comparator for the stepper
     * @param comparator the comparator to use
     * @return this builder
     */
    public MFXTableColumnOptions comparator(Comparator comparator) {
        this.comparator = comparator;
        return me();
    }

    /**
     * Get the comparator
     * @return the comparator
     */
    public Comparator getComparator() {
        return comparator;
    }
}
