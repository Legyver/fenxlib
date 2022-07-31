package com.legyver.fenxlib.widgets.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.layout.PopupDimensions;
import com.legyver.fenxlib.core.menu.options.ShowPopupMenuOption;
import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.core.menu.templates.section.AbstractMenuSection;
import javafx.scene.Parent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * A menu section that contains the about application menu item
 */
public class AboutMenuSection extends AbstractMenuSection implements MenuSection {
    private static final Logger logger = LogManager.getLogger(AboutMenuSection.class);

    /**
     * Construct a menu section containing a menu item to launch the about application page
     * @param aboutPageOptions the options to use to create the about page
     */
    public AboutMenuSection(AboutPageOptions aboutPageOptions) {
        super(Arrays.asList(
                        new ShowPopupMenuOption("About", createAboutPage(aboutPageOptions), initTarget())
                )
        );
    }

    private static Parent createAboutPage(AboutPageOptions aboutPageOptions) {
        try {
            return new AboutPageFactory().makeNode(null, aboutPageOptions);
        } catch (CoreException e) {
            logger.error("Error creating about page: " + e.getMessage(), e);
        }
        return null;
    }

    private static PopupDimensions initTarget() {
        return new PopupDimensions.Builder()
                .prefHeight(400.0)
                .prefWidth(500.0)
                .build();
    }
}
