package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.AccordionOptions;
import javafx.scene.control.Accordion;

/**
 * Factory to create an Accordion control
 */
public class AccordionFactory implements NodeFactory<Accordion, AccordionOptions> {

    @Override
    public Accordion makeNode(LocationContext locationContext, AccordionOptions options) throws CoreException {
        Accordion accordion = makeAccordion();
        Fenxlib.register(locationContext.decorateWith(options.getName()), accordion);
        return accordion;
    }

    @Override
    public AccordionOptions newOptions() {
        return new AccordionOptions();
    }

    /**
     * Factory method to instantiate an Accordion.
     * @return a javafx Accordion by default, override if you need something else
     */
    protected Accordion makeAccordion() {
        return new Accordion();
    }
}
