package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXDecorator;
import com.legyver.fenxlib.util.GuiUtil;
import com.legyver.fenxlib.util.hook.LifecycleHook;
import java.net.URL;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
		decorator.setOnCloseButtonAction(() -> {
			GuiUtil.executeHook(LifecycleHook.PRE_SHUTDOWN);
			Platform.exit();
		});
		decorator.setCustomMaximize(true);

		Scene scene = new Scene(decorator, width, height);
		if (stylesheetUrls != null) {
			Stream.of(stylesheetUrls).map(url -> url.toExternalForm())
					.forEach(styleSheet -> scene.getStylesheets().add(styleSheet));
		}

		return scene;
	}
}