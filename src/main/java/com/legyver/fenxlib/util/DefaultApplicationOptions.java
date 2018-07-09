package com.legyver.fenxlib.util;

import javafx.stage.Stage;
import com.legyver.fenxlib.locator.query.DefaultComponentRegistry;

public class DefaultApplicationOptions<U extends IUiModel> extends AbstractApplicationOptions<DefaultComponentRegistry, U> {

	public DefaultApplicationOptions(Stage primaryStage, U uiModel) {
		super(primaryStage, new DefaultComponentRegistry(), uiModel);
	}

}
