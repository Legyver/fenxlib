package com.legyver.fenxlib.util;

import com.legyver.fenxlib.uimodel.IUiModel;
import com.jfoenix.svg.SVGGlyphLoader;
import java.io.IOException;
import com.legyver.fenxlib.locator.IComponentRegistry;
import javafx.stage.Stage;
import com.legyver.fenxlib.factory.SvgIconFactory;
import com.legyver.fenxlib.locator.query.DefaultComponentRegistry;
import com.legyver.fenxlib.util.hook.LifecycleHook;

public class GuiUtil {

	private static IComponentRegistry registry = new DefaultComponentRegistry();
	private static ApplicationOptions customOptions;
	private static Stage stage;

	public static void init(ApplicationOptions<? extends IComponentRegistry, ? extends IUiModel> applicationOptions) throws IOException {
		GuiUtil.customOptions = applicationOptions;
		if (applicationOptions.getPrimaryStage() != null) {
			GuiUtil.stage = applicationOptions.getPrimaryStage();
		}
		if (applicationOptions.getComponentRegistry() != null) {
			GuiUtil.registry = applicationOptions.getComponentRegistry();
		}
		for (LoadableResource svgFile : applicationOptions.getIconFontSVGFiles()) {
			SVGGlyphLoader.loadGlyphsFont(SvgIconFactory.class.getResourceAsStream(svgFile.getPath() + svgFile.getName()), svgFile.getName());
		}
	}

	public static void setPrimaryStage(Stage stage) {
		GuiUtil.stage = stage;
	}

	public static Stage getPrimaryStage() {
		return stage;
	}

	public static <T extends IComponentRegistry> T getComponentRegistry() {
		return (T) registry;
	}

	public static <U extends IUiModel> U getUiModel() {
		return (U) customOptions.getUiModel();
	}

	public static ApplicationOptions getApplicationOptions() {
		return customOptions;
	}

	public static void executeHook(LifecycleHook hook) {
		if (customOptions != null) {
			customOptions.executeHook(hook);
		}
	}

}
