package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.AbstractSelectFileOrDirectoryFactory;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.FileOptionsChooserFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create an Open File Chooser with a specified title and type/extension filters
 * @Since 2.0
 */
public class SelectFileMenuFactory extends AbstractSelectFileOrDirectoryFactory<FileOptionsChooserFactory> {
	private final List<FileChooser.ExtensionFilter> extensionFilters;

	/**
	 * Construct an Open File Chooser with a specified title and type/extension filters
	 * @param extensionFilters file extensions to filter on
	 * @throws CoreException if there is an error retrieving the last browsed-to location
	 */
	public SelectFileMenuFactory(List<FileChooser.ExtensionFilter> extensionFilters) throws CoreException {
		super(new FileOptionsChooserFactory());
		this.extensionFilters = extensionFilters;
	}

	@Override
	public File choose(Action action, String message, FileOptionsChooserFactory chooserFactory, Stage primaryStage) {
		FileChooser fileChooser = chooserFactory.makeFileChooser(message, extensionFilters);
		return action.choose(fileChooser, primaryStage);
	}

	/**
	 * Fluent builder to create a list of {@link FileChooser.ExtensionFilter}
	 */
	public static class ExtensionFilterFactory {
		private final List<ExtensionFactory> extensionFactories = new ArrayList<>();

		/**
		 * Build a list of {@link FileChooser.ExtensionFilter}
		 * @return list of filters
		 */
		public List<FileChooser.ExtensionFilter> build() {
			return extensionFactories.stream()
					.map(f -> f.buildInternal())
					.collect(Collectors.toList());
		}

		/**
		 * Create a filter entry called typeName
		 * @param typeName the name of the filter
		 * @return the ExtensionFactory builder to specify one-or-more file extensions
		 */
		public ExtensionFactory typeName(String typeName) {
			ExtensionFactory extensionFactory = new ExtensionFactory(typeName, this);
			extensionFactories.add(extensionFactory);
			return extensionFactory;
		}

	}

	/**
	 * child builder to specify file extensions with a filter entry
	 */
	public static class ExtensionFactory {
		private final ExtensionFilterFactory parentFactory;
		private final String typeName;
		private List<String> extensions = new ArrayList<>();

		private ExtensionFactory(String typeName, ExtensionFilterFactory parentFactory) {
			this.typeName = typeName;
			this.parentFactory = parentFactory;
		}

		private FileChooser.ExtensionFilter buildInternal() {
			return new FileChooser.ExtensionFilter(typeName, extensions);
		}

		/**
		 * Shortcut to {@link ExtensionFilterFactory#build}
		 * @return list of filters
		 */
		public List<FileChooser.ExtensionFilter> build() {
			return parentFactory.build();
		}

		/**
		 * Add a file extension to the type
		 * @param extension extension to add
		 * @return the ExtensionFactory to keep adding extensions
		 */
		public ExtensionFactory extension(String extension) {
			extensions.add(extension);
			return this;
		}

		/**
		 * Shortcut to {@link ExtensionFilterFactory#typeName(String)}
		 * @param typeName the type name
		 * @return the ExtensionFactory to add file extensions
		 */
		public ExtensionFactory typeName(String typeName) {
			return parentFactory.typeName(typeName);
		}
	}

}
