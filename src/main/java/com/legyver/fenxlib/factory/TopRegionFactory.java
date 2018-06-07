package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import com.legyver.fenxlib.factory.menu.CenterOptions;
import com.legyver.fenxlib.factory.menu.LeftMenuOptions;
import com.legyver.fenxlib.factory.menu.MenuFactory;
import com.legyver.fenxlib.factory.menu.RightMenuOptions;

public class TopRegionFactory implements SpaceableFactory, RegionFactory {
	private final LeftMenuOptions leftOptions;
	private final CenterOptions centerOptions;
	private final RightMenuOptions rightOptions;

	public TopRegionFactory(LeftMenuOptions leftOptions, CenterOptions centerOptions, RightMenuOptions rightOptions) {
		this.leftOptions = leftOptions;
		this.centerOptions = centerOptions;
		this.rightOptions = rightOptions;
	}

	public TopRegionFactory(LeftMenuOptions leftOptions, RightMenuOptions rightOptions) {
		this(leftOptions, null, rightOptions);
	}

	private HBox createMenuBars(RegionInitializationOptions regionInitOptions) throws CoreException {
		MenuBar leftBar = getMenuBar(leftOptions.getFactories());
		MenuBar rightBar = getMenuBar(rightOptions.getFactories());
		if (centerOptions != null) {
			Node center = centerOptions.getFactory().makeNode(regionInitOptions.getLocationContext());
			center.getStyleClass().add("menubar-center");
			if (center instanceof Region) {
				((Region) center).minWidth(100);
			}
			if (center instanceof TextField) {
				TextField textField = (TextField) center;
				textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					textField.setPrefWidth(textField.getText().length() * 4); //an assumption of width of character in pixels
				});
				textField.setAlignment(Pos.CENTER);
			}
			HBox.setHgrow(center, Priority.ALWAYS);
			return spaceNodes(leftBar, center, rightBar);
		} else {

		}
		return spaceNodes(leftBar, rightBar);
	}

	private MenuBar getMenuBar(MenuFactory[] menuFactories) {
		MenuBar bar = new MenuBar();
		if (menuFactories != null) {
			List<Menu> menus = Stream.of(menuFactories)
					.map(f -> f.makeMenu())
					.collect(Collectors.toList());
			bar.getMenus().addAll(menus);
		}
		return bar;
	}

	@Override
	public Region makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		return createMenuBars(regionInitOptions);
	}

}
