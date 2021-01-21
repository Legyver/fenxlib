package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.menu.CenterOptions;
import com.legyver.fenxlib.core.impl.factory.menu.LeftMenuOptions;
import com.legyver.fenxlib.core.impl.factory.menu.MenuFactory;
import com.legyver.fenxlib.core.impl.factory.menu.RightMenuOptions;
import com.legyver.fenxlib.core.impl.factory.options.RegionInitializationOptions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.value.ObservableValue;
import javafx.css.Styleable;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;


/**
 * Factory to create the menu bar region in a window
 *  LeftMenuOptions: left-aligned menus
 *  RightMenuOptions: right-aligned menus
 *  CenterOptions: what goes in between
 */
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
			Styleable center = centerOptions.getFactory().makeNode(regionInitOptions.getLocationContext());
			center.getStyleClass().add("menubar-center");
			if (center instanceof Region) {
				((Region) center).minWidth(100);
			}
			if (center instanceof TextField) {
				TextField textField = (TextField) center;
				textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (newValue != null) {
						textField.setPrefWidth(textField.getText().length() * 4); //an assumption of width of character in pixels
					}
				});
				textField.setAlignment(Pos.CENTER);

				HBox.setHgrow(textField, Priority.ALWAYS);
				return spaceNodes(leftBar, textField, rightBar);
			}
		}
		return spaceNodes(leftBar, rightBar);
	}

	private MenuBar getMenuBar(MenuFactory[] menuFactories) throws CoreException {
		MenuBar bar = new MenuBar();
		if (menuFactories != null) {
			List<Menu> menus = unwrap(() -> Stream.of(menuFactories)
					.map(f -> wrap(() -> f.makeMenu()))
					.collect(Collectors.toList()));
			bar.getMenus().addAll(menus);
		}
		return bar;
	}

	@Override
	public Region makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		return createMenuBars(regionInitOptions);
	}

}
