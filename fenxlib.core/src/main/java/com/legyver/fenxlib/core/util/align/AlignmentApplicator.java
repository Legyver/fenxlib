package com.legyver.fenxlib.core.util.align;

import javafx.css.Styleable;
import javafx.geometry.Pos;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

/**
 * Utility class to apply an alignment to a specified control
 */
public class AlignmentApplicator {
    private final Pos pos;

    /**
     * Construct an applicator to apply an alignment to a specified control
     * @param pos the alignment to apply
     */
    public AlignmentApplicator(Pos pos) {
        this.pos = pos;
    }

    /**
     * Apply the alignment to the specified control
     * @param control the control to apply the alignment to
     * @return true if the alignment was applied
     */
    public boolean applyAlignment(Styleable control) {
        boolean success = true;
        if (control instanceof TextField) {
            ((TextField) control).setAlignment(pos);
        } else if (control instanceof Labeled) {
            ((Labeled) control).setAlignment(pos);
        } else {
            success = false;
        }
        return success;
    }
}
