package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.extensions.materialfx.controls.factory.MFXTableColumnFactory;
import io.github.palexdev.materialfx.controls.MFXTableColumn;

import java.util.Comparator;

public class MFXTableColumnOptions extends BaseControlBuilder<MFXTableColumnOptions> implements StyleableControlOptions<MFXTableColumn> {
    private String text;
    private Boolean resizable;
    private Comparator comparator;

    public MFXTableColumnOptions text(String text) {
        this.text = text;
        return me();
    }

    public String getText() {
        return text;
    }

    public MFXTableColumnOptions resizable(boolean resizable) {
        this.resizable = resizable;
        return me();
    }

    public Boolean getResizable() {
        return resizable;
    }

    public MFXTableColumnOptions comparator(Comparator comparator) {
        this.comparator = comparator;
        return me();
    }

    public Comparator getComparator() {
        return comparator;
    }
}
