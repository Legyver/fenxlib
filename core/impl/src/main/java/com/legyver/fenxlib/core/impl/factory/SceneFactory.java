package com.legyver.fenxlib.core.impl.factory;

import com.jfoenix.controls.JFXDecorator;
import com.legyver.fenxlib.core.impl.util.hook.HookExecutingAction;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.util.hook.decorator.execution.ShutdownHookDecorator;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.stream.Stream;

public class SceneFactory {
	private final URL[] stylesheetUrls;
	private final Stage stage;
	private final double width;
	private final double height;

	public SceneFactory(Stage stage, double width, double height, URL... stylesheetUrls) {
		this.stylesheetUrls = stylesheetUrls;
		this.stage = stage;
		this.width = width;
		this.height = height;
	}

	public Scene makeScene(BorderPane root) {
		JFXDecorator decorator = new JFXDecorator(stage, root);
		decorator.setOnCloseButtonAction(new ShutdownHookDecorator(new HookExecutingAction(LifecyclePhase.PRE_SHUTDOWN))::execute);
		decorator.setCustomMaximize(true);

		Scene scene = new Scene(decorator, width, height);
		if (stylesheetUrls != null) {
			Stream.of(stylesheetUrls).map(url -> url.toExternalForm())
					.forEach(styleSheet -> scene.getStylesheets().add(styleSheet));
		}

		return scene;
	}
}
