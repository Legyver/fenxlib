package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Accordion;

/**
 * Factory to create an Accordion control
 */
public class AccordionFactory implements NodeFactory<Accordion> {
    @Override
    public Accordion makeNode(LocationContext locationContext) throws CoreException {
        Accordion accordion = makeAccordion();
        Fenxlib.register(locationContext, accordion);
        return accordion;
    }

    /**
     * Factory method to instantiate an Accordion.
     * @return a javafx Accordion by default, override if you need something else
     */
    protected Accordion makeAccordion() {
        return new Accordion();
    }
}
