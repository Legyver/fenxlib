package com.legyver.fenxlib.config.options;

import com.legyver.fenxlib.config.load.AppConfigProvider;
import com.legyver.fenxlib.config.load.AppHome;
import com.legyver.fenxlib.uimodel.IUiModel;
import javafx.stage.Stage;
import com.legyver.fenxlib.locator.query.DefaultComponentRegistry;

public class DefaultApplicationOptions<U extends IUiModel> extends AbstractApplicationOptions<DefaultComponentRegistry, U> {
	private final AppConfigProvider appConfigProvider;
	
	public DefaultApplicationOptions(AppConfigProvider appConfigProvider, Stage primaryStage, U uiModel) {
		super(primaryStage, new DefaultComponentRegistry(), uiModel);
		this.appConfigProvider = appConfigProvider;
	}

	public DefaultApplicationOptions(String appName, Stage primaryStage, U uiModel) {
		this(new AppHome(appName), primaryStage, uiModel);
	}

	public AppConfigProvider getAppConfigProvider() {
		return appConfigProvider;
	}
}
