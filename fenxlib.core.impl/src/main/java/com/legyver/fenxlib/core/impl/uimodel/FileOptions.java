package com.legyver.fenxlib.core.impl.uimodel;

import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Abstraction around a file in the application
 */
public interface FileOptions extends Comparable<FileOptions>  {

	/**
	 * Retrieve the file
	 * @return get the file
	 */
	File getFile();

	/**
	 * Set if the file is new
	 * @param newFile true if the file is new
	 */
	void setNewFile(boolean newFile);

	/**
	 * Retrieve if the new file flag has been set
	 * @return the new file flag
	 */
	boolean isNewFile();

	/**
	 * Retrieve the property backing the new file flag
	 * @return the new file flag property
	 */
	BooleanProperty newFileProperty();

	/**
	 * Set the (simple) name
	 * @param name the (simple) name
	 */
	void setFileName(String name);

	/**
	 * Retrieve the name of the file
	 * @return the name of the file
	 */
	String getFileName();

	/**
	 * Retrieve the property backing the file name
	 * @return the file name property
	 */
	StringProperty fileNameProperty();

	/**
	 * Set the (absolute) path to the file
	 * @param absolutePath the path
	 */
	void setFilePath(String absolutePath);

	/**
	 * Retrieve the (absolute) path to the file
	 * @return the path
	 */
	String getFilePath();

	/**
	 * Retrieve the property backing the (absolute) file path
	 * @return the file path property
	 */
	StringProperty filePathProperty();

}
