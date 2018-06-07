package com.legyver.fenxlib.util;

import com.jfoenix.svg.SVGGlyphLoader;
import java.io.IOException;
import com.legyver.fenxlib.locator.IComponentRegistry;
import javafx.stage.Stage;
import com.legyver.fenxlib.factory.SvgIconFactory;
import com.legyver.fenxlib.util.hook.LifecycleHook;

public class GuiUtil {
	private static ApplicationOptions applicationOptions;

	public static void init(ApplicationOptions applicationOptions) throws IOException {
		GuiUtil.applicationOptions = applicationOptions;
		SVGGlyphLoader.loadGlyphsFont(SvgIconFactory.class.getResourceAsStream("/fonts/icomoon.svg"), "icomoon.svg");
	}

	public static Stage getPrimaryStage() {
		return applicationOptions.getPrimaryStage();
	}

	public static <T extends IComponentRegistry> T getComponentRegistry() {
		return (T) applicationOptions.getComponentRegistry();
	}

	public static <U extends IUiModel> U getUiModel() {
		return (U) applicationOptions.getUiModel();
	}

	public static void executeHook(LifecycleHook hook) {
		applicationOptions.executeHook(hook);
	}


}
