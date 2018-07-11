package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.context.BladeContext;
import com.legyver.fenxlib.factory.options.blade.BladeOption;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;
import com.legyver.fenxlib.factory.options.visitor.CenterItemVisitor;
import com.legyver.fenxlib.factory.options.visitor.ConstraintVisitor;
import com.legyver.fenxlib.factory.options.visitor.LeftItemVisitor;
import com.legyver.fenxlib.factory.options.visitor.RightItemVisitor;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.LocationContextDecorator;
import com.legyver.fenxlib.widget.MoreField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;

public class BladeFactory implements TitledPaneContentFactory<VBox> {
	private final BladeOption[] bladeOptions;
	private final int letterWidth;

	public BladeFactory(int letterWidth, BladeOption... bladeOptions) {
		this.bladeOptions = bladeOptions;
		this.letterWidth = letterWidth;
	}

	public BladeFactory(BladeOption... bladeOptions) {
		this(4, bladeOptions);
	}

	@Override
	public VBox makeNode(LocationContext locationContext) throws CoreException {
		VBox vbox = new VBox();
		GridPane gp = new GridPane();
		vbox.getChildren().add(gp);
		gp.setGridLinesVisible(false);
		gp.setVgap(10);
		if (bladeOptions != null) {
			BladeContext bladeContext = new BladeContext(gp);
			for (int i = 0; i < bladeOptions.length; i++) {
				BladeOption bladeOption = bladeOptions[i];
				LocationContext decoratedLocation = new LocationContextDecorator(locationContext);
				//TODO: maybe these should be configurable?  The reason I'm not doing it now is that in an ideal world, all options should be natively supported without extension. See TODO: in BladeOption
				bladeOption.accept(new LeftItemVisitor(gp, decoratedLocation, bladeContext), i);
				bladeOption.accept(new CenterItemVisitor(gp, decoratedLocation, bladeContext), i);//decoratedContext.name gets set here
				bladeOption.accept(new RightItemVisitor(gp, decoratedLocation, bladeContext), i);
				bladeOption.accept(new ConstraintVisitor(gp, decoratedLocation, bladeContext), i);
			}
			
			MoreField moreTrigger = bladeContext.getMoreTrigger();
			GridPane moreGrid = bladeContext.getMoreGrid();
			if (moreTrigger != null) {
				moreTrigger.expandedProperty().addListener(new ChangeListener<Boolean>(){
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
						if (newValue) {
							vbox.getChildren().add(moreGrid);
						} else {
							vbox.getChildren().remove(moreGrid);
						}
					}
				});
			}
			
		}
		return vbox;
	}


}
