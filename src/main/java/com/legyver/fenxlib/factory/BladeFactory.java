package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.BladeOption;
import com.legyver.fenxlib.factory.options.visitor.CenterItemVisitor;
import com.legyver.fenxlib.factory.options.visitor.ConstraintVisitor;
import com.legyver.fenxlib.factory.options.visitor.LeftItemVisitor;
import com.legyver.fenxlib.factory.options.visitor.RightItemVisitor;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.LocationContextDecorator;

public class BladeFactory implements TitledPaneContentFactory<GridPane> {
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
	public GridPane makeNode(LocationContext locationContext) throws CoreException {
		GridPane gp = new GridPane();
		gp.setGridLinesVisible(false);
		gp.setVgap(10);
		if (bladeOptions != null) {
			for (int i = 0; i < bladeOptions.length; i++) {
				BladeOption bladeOption = bladeOptions[i];
				LocationContext decoratedContext = new LocationContextDecorator(locationContext);
				bladeOption.accept(new LeftItemVisitor(gp, decoratedContext), i);
				bladeOption.accept(new CenterItemVisitor(gp, decoratedContext), i);//decoratedContext.name gets set here
				bladeOption.accept(new RightItemVisitor(gp, decoratedContext), i);
				bladeOption.accept(new ConstraintVisitor(gp, decoratedContext), i);
			}
		}
		return gp;
	}


}
