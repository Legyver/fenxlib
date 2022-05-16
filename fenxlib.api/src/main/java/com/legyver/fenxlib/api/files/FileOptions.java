package com.legyver.fenxlib.api.files;

import java.io.File;
import java.util.Map;

import com.legyver.utils.nippe.Base;
import com.legyver.utils.nippe.Step;
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
	 * Get the file extension associated with a file.
	 * The mechanism is to get the last part of the file succeeding the last '.'
	 * @return returns the file extension if one can be determined or null if one does not exist or the file is null
	 */
	default String getFileExtension() {
		return new Step<>(new Base<>(getFile()),
				file -> {
			String[] parts = file.getName().split("\\.");
			if (parts.length == 1) {
				return null;
			} else {
				return parts[parts.length - 1];
			}
		}).execute();
	}

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

	/**
	 * Get the metadata associated with the file
	 * @return the metadata
	 */
	Map<String, Object> getMetadata();

	/**
	 * Get the metadata value associated with a key
	 * @param key the metadata value key
	 * @return the value from the metadata.  Returns null if value not set in metadata.
	 */
	default Object getMetadata(String key) {
		return new Step<>(
				new Base<>(
						getMetadata()
				),
				metadata -> metadata.get(key)
		).execute();
	}

	/**
	 * Get the metadata value associated with a key
	 * @param key the metadata value key
	 * @return the value form the metadata as a string.  Returns null if value not set in metadata
	 */
	default String getMetaDataAsString(String key) {
		return new Step<>(
				new Base<>(getMetadata(key)),
				val -> val.toString()
		).execute();
	}

	/**
	 * Set a value in metadata
	 * @param key the key to associate the value with in the metadata
	 * @param value the value to set on the metadata
	 */
	default void addMetadata(String key, Object value) {
		new Step<>(
				new Base<>(getMetadata()),
				metadata -> metadata.put(key, value)
		).execute();
	}

	/**
	 * Get the Content-Type of the file
	 * @return the content type.  If none is set returns null.
	 */
	default String getContentType() {
		return getMetaDataAsString("Content-Type");
	}

	/**
	 * Set the Content-Type of the file
	 * @param contentType the content type
	 */
	default void setContentType(String contentType) {
		addMetadata("Content-Type", contentType);
	}
}
