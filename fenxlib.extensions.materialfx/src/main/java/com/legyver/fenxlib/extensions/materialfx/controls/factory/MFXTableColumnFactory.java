package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXTableColumn;

import java.util.Comparator;

/**
 * Factory to produce a MFXTableColumn
 */
public class MFXTableColumnFactory implements NodeFactory<MFXTableColumn> {
    /**
     * Param to specify the name of the column
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Param to specify if the column is resizable
     */
    public static final String CONSTRUCTOR_PARAM_RESIZABLE = "resizable";
    /**
     * Param to specify the comparator for the column values
     */
    public static final String CONSTRUCTOR_PARAM_COMPARATOR = "comparator";

    private final String text;
    private final Boolean resizable;
    private final Comparator comparator;

    /**
     * Construct a factory to produce MFXTableColumn
     * @param text the name of the column
     * @param resizable if the column is resizable
     * @param comparator a comparator for sorting the column values
     */
    public MFXTableColumnFactory(String text, Boolean resizable, Comparator comparator) {
        this.text = text;
        this.resizable = resizable;
        this.comparator = comparator;
    }

    @Override
    public MFXTableColumn makeNode(LocationContext locationContext) throws CoreException {
        MFXTableColumn tableColumn = new MFXTableColumn();
        return tableColumn;
    }
}
