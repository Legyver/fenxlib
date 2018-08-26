package com.legyver.fenxlib.util;

import com.legyver.fenxlib.factory.SvgIconFactory.IconFont;
import com.legyver.fenxlib.uimodel.IUiModel;
import javafx.stage.Stage;
import com.legyver.fenxlib.locator.query.DefaultComponentRegistry;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Supplies a {@link DefaultComponentRegistry} and a default
 * {@link IconFont.ICOMOON} icon font set.
 */
public class DefaultApplicationOptions<U extends IUiModel> extends AbstractApplicationOptions<DefaultComponentRegistry, U> {

	public DefaultApplicationOptions(Stage primaryStage, U uiModel) {
		super(primaryStage, new DefaultComponentRegistry(), uiModel);
	}

	@Override
	public List<LoadableResource> getIconFontSVGFiles() {
		IconFont defaultIconFont = IconFont.ICOMOON;
		return Arrays.asList(new LoadableResource(defaultIconFont.getResourcePath(), defaultIconFont.getSVGFileName()));
	}

}
