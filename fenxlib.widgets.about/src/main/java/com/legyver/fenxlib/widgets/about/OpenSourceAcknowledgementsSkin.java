package com.legyver.fenxlib.widgets.about;

import com.legyver.fenxlib.widgets.license.OpenSourceReferenceList;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import static com.legyver.fenxlib.api.controls.utils.TextFactoryUtils.getText;

/**
 * Skin for the OpenSourceAcknowledgements control
 */
public class OpenSourceAcknowledgementsSkin extends SkinBase<OpenSourceAcknowledgements> {
    private final VBox layout;
    private final TextFlow openSourceClause;
    private final OpenSourceReferenceList openSourceReferenceList;

    /**
     * Construct a skin for an OpenSourceAcknowledgements control
     * @param openSourceAcknowledgements the control to skin
     */
    public OpenSourceAcknowledgementsSkin(OpenSourceAcknowledgements openSourceAcknowledgements) {
        super(openSourceAcknowledgements);

        openSourceClause = getPowerFlow(openSourceAcknowledgements);
        openSourceReferenceList = new OpenSourceReferenceList(openSourceAcknowledgements.getOpenSourceLicenseProperties());
        layout = new VBox(openSourceClause, openSourceReferenceList);

        getChildren().add(layout);
    }

    private TextFlow getPowerFlow(OpenSourceAcknowledgements openSourceAcknowledgements) {
        String poweredByClause = openSourceAcknowledgements.getOpenSourceTagLine();
        Text textPoweredBy = getText(poweredByClause);
        textPoweredBy.setId("powered-by");
        return new TextFlow(textPoweredBy);
    }
}
