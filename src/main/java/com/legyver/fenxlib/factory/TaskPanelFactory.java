package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import com.legyver.fenxlib.factory.decorator.ButtonIconDecorator;
import com.legyver.fenxlib.factory.decorator.ButtonTooltipDecorator;
import com.legyver.fenxlib.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.factory.options.IconOptions;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.query.ComponentQuery;
import com.legyver.fenxlib.util.GuiUtil;

public class TaskPanelFactory implements TitledPaneContentFactory<AnchorPane> {
	public static final String TASKS_MENU_TOGGLE_BUTTON = "Tasks";
	public static final String TASKS_PANE_TITLE = "Task Center";
	public static final String TASKS_LIST = "tasks";
	private final SplitPaneFactory factory;

	public TaskPanelFactory() {
		this.factory = new SplitPaneFactory(.09, getTaskToolbarFactory(), getTaskListFactory());
	}

	private HBoxFactory getTaskListFactory() {
		return new HBoxFactory(Pos.CENTER, new JFXListViewFactory(TASKS_LIST));
	}

	private SpreadHBoxFactory getTaskToolbarFactory() {
		return new SpreadHBoxFactory(new LabelFactory(TASKS_MENU_TOGGLE_BUTTON), getClearIconFactory());
	}

	private ButtonTooltipDecorator getClearIconFactory() {

		EventHandler<ActionEvent> clearTasks = (ActionEvent event) -> {
			Optional<ListView> tasksQuery = new ComponentQuery.QueryBuilder(GuiUtil.getComponentRegistry())
					.inRegion(BorderPaneInitializationOptions.REGION_RIGHT)
					.inSubRegion(TaskPanelFactory.TASKS_PANE_TITLE)
					.named(TaskPanelFactory.TASKS_LIST).execute();
			ListView tasks = tasksQuery.get();
			tasks.getItems().clear();
		};
		ButtonTooltipDecorator clear = new ButtonTooltipDecorator("Clear tasks", new ButtonIconDecorator(clearTasks, new SvgIconFactory(new IconOptions("trash", "#999999", 15))));

		return clear;
	}

	@Override
	public AnchorPane makeNode(LocationContext lc) throws CoreException {
		AnchorPane pane = new AnchorPane();
		
		SplitPane splitPane = factory.makeNode(lc);
		splitPane.setMinHeight(600);
		pane.minHeightProperty().bind(splitPane.heightProperty());
		pane.maxWidthProperty().bind(splitPane.widthProperty());
		pane.getChildren().add(splitPane);
		return pane;
	}

}
