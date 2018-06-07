package com.legyver.fenxlib.widget;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import com.legyver.fenxlib.factory.options.DirectoryOptions;
import com.legyver.fenxlib.util.GuiUtil;

public class DirectoryOptionsChooser {

	private final String title;
	private final DirectoryOptions directoryOptions;

	public DirectoryOptionsChooser(String title, DirectoryOptions directoryOptions) {
		this.title = title;
		this.directoryOptions = directoryOptions;
	}

	public void show() {
		final DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle(title);
		Stage stage = GuiUtil.getPrimaryStage();
		File dir = dirChooser.showDialog(stage);
		if (dir != null) {
			directoryOptions.setSourceDir(dir);
			directoryOptions.setSourceDirName(dir.getAbsolutePath());
		}
	}

}
