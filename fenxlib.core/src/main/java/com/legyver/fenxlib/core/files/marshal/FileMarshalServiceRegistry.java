package com.legyver.fenxlib.core.files.marshal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.core.files.marshal.contenttype.ContentTypeBasedFileMarshal;
import com.legyver.fenxlib.core.files.marshal.exception.UnmarshalableContentTypeException;
import com.legyver.fenxlib.core.files.marshal.exception.UnmarshalableFileException;
import com.legyver.fenxlib.core.files.marshal.exception.UnmarshalableFileExtensionException;
import com.legyver.fenxlib.core.files.marshal.extension.ExtensionBasedFileMarshal;
import com.legyver.fenxlib.api.service.IOrderedServiceDelegator;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;

import java.util.*;

/**
 * Registry for file marshallers
 */
public class FileMarshalServiceRegistry {
    private static FileMarshalServiceRegistry instance;
    private final Map<String, IOrderedServiceDelegator<ExtensionBasedFileMarshal>> fileMarshalsByFileSuffix;
    private final Map<String, IOrderedServiceDelegator<ContentTypeBasedFileMarshal>> fileMarshalsByContentType;

    private FileMarshalServiceRegistry() {
        ServiceLoader<ExtensionBasedFileMarshal> extFileMarshalServiceLoader = ServiceLoader.load(ExtensionBasedFileMarshal.class);
        IOrderedServiceDelegator<ExtensionBasedFileMarshal> extensionBasedOrderedServiceDelegator = new OrderedServiceDelegator<>(extFileMarshalServiceLoader);
        fileMarshalsByFileSuffix = extensionBasedOrderedServiceDelegator.split(fileMarshal -> fileMarshal.getSupportedFileSuffix());

        ServiceLoader<ContentTypeBasedFileMarshal> cntFileMarshalServiceLoader = ServiceLoader.load(ContentTypeBasedFileMarshal.class);
        IOrderedServiceDelegator<ContentTypeBasedFileMarshal> cntBasedOrderedServiceDelegator = new OrderedServiceDelegator<>(cntFileMarshalServiceLoader);
        fileMarshalsByContentType = cntBasedOrderedServiceDelegator.split(fileMarshal -> fileMarshal.getSupportedContentType());
    }

    /**
     * Get the file marshaller registry
     * @return the registry
     */
    public static FileMarshalServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (FileMarshalServiceRegistry.class) {
                if (instance == null) {
                    instance = new FileMarshalServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Marshal an object to the file.
     * This first looks up the marshaller by content-type.
     * If no content-type is present or there is no marshaller for the specified content-type,
     * it will try to look up the marshaller by file extension.
     * If no file extension is determinable, or there is no marshaller for the specified extension,
     * it will throw an exception.
     * @param content the content to marshall
     * @param fileOptions options describing the file
     * @throws CoreException if no marshaller exists, or there is an error during marshalling or saving the file
     */
    public void marshal(Object content, FileOptions fileOptions) throws CoreException {
        validateContentAndFileOptions(content, fileOptions);
        try {
            marshalByContentTypeInternal(content, fileOptions);
        } catch (UnmarshalableContentTypeException exception) {
            marshalByFileExtensionInternal(content, fileOptions);
        }
    }

    /**
     * Marshal an object to the file.
     * Try to look up the marshaller by file extension and marshal the object to the file.
     * If no file extension is determinable, or there is no marshaller for the specified extension,
     * it will throw an exception.
     * @param content the content to marshall
     * @param fileOptions options describing the file
     * @throws CoreException if no marshaller exists, or there is an error during marshalling or saving the file
     */
    public void marshalByFileExtension(Object content, FileOptions fileOptions) throws CoreException {
        validateContentAndFileOptions(content, fileOptions);
        marshalByFileExtensionInternal(content, fileOptions);
    }

    private void marshalByFileExtensionInternal(Object content, FileOptions fileOptions) throws CoreException {
        String fileExtension = fileOptions.getFileExtension();
        if (fileExtension == null) {
            throw new UnmarshalableFileExtensionException("Unable to marshal file when extension is unknown for file " + fileOptions.getFileName());
        }
        IOrderedServiceDelegator<ExtensionBasedFileMarshal> fileMarshal = fileMarshalsByFileSuffix.get(fileExtension);
        if (fileMarshal ==  null || fileMarshal.isEmpty()) {
            throw new UnmarshalableFileExtensionException("Unknown content type: " );
        }
        ExtensionBasedFileMarshal delegate = fileMarshal.getDelegate();
        delegate.marshal(content, fileOptions);
    }

    /**
     * Marshal an object to the file.
     * Try to look up the marshaller by content-type and marshal the object to the file.
     * If no content-type is specified, or there is no marshaller for the specified content-type,
     * it will throw an exception.
     * @param content the content to marshall
     * @param fileOptions options describing the file
     * @throws CoreException if no marshaller exists, or there is an error during marshalling or saving the file
     */
    public void marshalByContentType(Object content, FileOptions fileOptions) throws CoreException {
        validateContentAndFileOptions(content, fileOptions);
        marshalByContentTypeInternal(content, fileOptions);
    }

    private void marshalByContentTypeInternal(Object content, FileOptions fileOptions) throws CoreException {

        String contentType = fileOptions.getContentType();
        if (contentType == null) {
            throw new UnmarshalableContentTypeException("No content type specified");
        }
        IOrderedServiceDelegator<ContentTypeBasedFileMarshal> fileMarshal = fileMarshalsByContentType.get(contentType);
        if (fileMarshal == null || fileMarshal.isEmpty()) {
            throw new UnmarshalableContentTypeException("Unknown content type: " );
        }
        ContentTypeBasedFileMarshal delegate = fileMarshal.getDelegate();
        delegate.marshal(content, fileOptions);
    }

    private void validateContentAndFileOptions(Object content, FileOptions fileOptions) throws UnmarshalableFileException {
        if (fileOptions == null) {
            throw new UnmarshalableFileException("FileOptions are null");
        }
        if (content == null) {
            throw new UnmarshalableFileException("Content is null");
        }
    }
}
