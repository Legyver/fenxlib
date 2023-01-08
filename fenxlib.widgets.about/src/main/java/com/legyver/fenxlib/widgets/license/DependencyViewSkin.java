package com.legyver.fenxlib.widgets.license;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.scene.controls.options.LabelOptions;
import com.legyver.fenxlib.api.scene.text.options.TextOptions;
import com.legyver.fenxlib.core.web.DesktopWeblink;
import javafx.geometry.HPos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Skin for a LicenseView
 */
public class DependencyViewSkin extends SkinBase<DependencyView> {
    private static final Logger logger = LogManager.getLogger(DependencyViewSkin.class);

    private final GridPane gridPane;

    /**
     * Skin a license view
     * @param dependencyView the license view to skin
     */
    public DependencyViewSkin(DependencyView dependencyView) {
        super(dependencyView);
        this.gridPane = new GridPane();

        DependencyData dependencyData = dependencyView.getItem();
        try {
            int rowCursor = 0;

            Label libraryLabel = ControlsFactory.make(Label.class, new LabelOptions().text("legyver.defaults.label.about.opensource.library"));
            gridPane.add(libraryLabel, 0, rowCursor);

            Label libraryName = ControlsFactory.make(Label.class, new LabelOptions().text(dependencyData.getName()));
            gridPane.add(libraryName, 1, rowCursor);

            LocalDate retrieved = dependencyData.getRetrieved();
            if (retrieved != null) {
                Label retrievedLabel = ControlsFactory.make(Label.class, new LabelOptions().text("legyver.defaults.label.about.opensource.retrieved"));
                gridPane.add(retrievedLabel, 0, ++rowCursor);

                Label retrievedDate = ControlsFactory.make(Label.class, new LabelOptions().text(retrieved.format(DateTimeFormatter.ISO_LOCAL_DATE)));
                gridPane.add(retrievedDate, 1, rowCursor);
            }

            List<DependencyData.TextLink> authors = dependencyData.getAuthors();
            if (!authors.isEmpty()) {
                layoutListSpan(authors, "legyver.defaults.label.about.opensource.authors", ++rowCursor);
            }
            rowCursor += authors.size();

            List<DependencyData.TextLink> titles = dependencyData.getTitles();
            if (!titles.isEmpty()) {
                layoutListSpan(titles, "legyver.defaults.label.about.opensource.titles", ++rowCursor);
            }
            rowCursor += titles.size();

            List<DependencyData.TextLink> copyrights = dependencyData.getLicenses();
            if (!copyrights.isEmpty()) {
                layoutListSpan(copyrights, "legyver.defaults.label.about.opensource.license", ++rowCursor);
            }
            rowCursor += copyrights.size();

            List<DependencyData.OrderedText> changes = dependencyData.getChanges();
            if (!changes.isEmpty()) {
                String text = "legyver.defaults.label.about.opensource.changes";
                Label label = ControlsFactory.make(Label.class, new LabelOptions().text(text));
                gridPane.add(label, 0, ++rowCursor, 2, 1);

                for (DependencyData.OrderedText orderedText : changes) {
                    TextFlow flow = ControlsFactory.make(TextFlow.class);
                    Text change = ControlsFactory.make(Text.class, new TextOptions()
                            .text(orderedText.getText())
                    );
                    flow.getChildren().add(change);
                    gridPane.add(flow, 1, rowCursor++);
                }
            }
        } catch (CoreException coreException) {
            logger.error("Error constructing LicenseViewSkin", coreException);
        }
        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(80, 120, 800, Priority.SOMETIMES, HPos.LEFT, true),
                new ColumnConstraints(120, 400, 800, Priority.ALWAYS, HPos.LEFT, true)
        );
        getChildren().add(gridPane);
    }

    private void layoutListSpan(List<DependencyData.TextLink> values, String text, int start) throws CoreException {
        int colSpan = 1;
        int rowSpan = values.size();
        Label label = ControlsFactory.make(Label.class, new LabelOptions().text(text));
        gridPane.add(label, 0, start, colSpan, rowSpan);

        int offset = 0;
        for (DependencyData.TextLink data : values) {
            Hyperlink link = new DesktopWeblink(data.getText(), data.getLink());
            link.getStyleClass().add("license-link");
            gridPane.add(link, 1, start + offset);
            offset++;
        }
    }
}
