package com.legyver.fenxlib.api.io;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.DefaultFileOptions;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.files.marshal.FileMarshalServiceRegistry;
import com.legyver.fenxlib.api.io.content.OutputContentWrapper;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Registry of IO Services
 */
public class IOServiceRegistry {
    private static final Logger logger = LogManager.getLogger(IOServiceRegistry.class);

    private final OrderedServiceDelegator<IOService> orderedServiceDelegator;
    private static IOServiceRegistry instance;

    private IOServiceRegistry() {
        ServiceLoader<IOService> configServiceLoader = ServiceLoader.load(IOService.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(configServiceLoader);
    }

    /**
     * Get the singleton instance of the ConfigLoaderServiceRegistry
     * @return the registry
     */
    public static IOServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (IOServiceRegistry.class) {
                if (instance == null) {
                    instance = new IOServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Acquire an input stream from a specified source file/resource
     *
     * @param appName the name of the application used in determining the application home
     * @param name the name of the file/resource
     * @param relativeToApplicationHome true if the file path (name) should be resolved relative to the application home
     * @return the input stream of the file/resources
     * @throws CoreException if there is an error determining a loader to use, or loading the input stream
     */
    public InputStream loadInputStream(String appName, String name, boolean relativeToApplicationHome) throws CoreException {
        InputStream loaded = null;
        CoreException thrownException = null;
        for (Iterator<IOService> it = orderedServiceDelegator.iterator(); it.hasNext() && loaded == null; ) {
            IOService service = it.next();
            logger.debug("Trying to load file {} using {} with priority {}.", name, service, service.order());
            try {
                loaded = service.loadInputStream(appName, name, relativeToApplicationHome);
            } catch (CoreException coreException) {
                logger.warn(service + " failed to load file " + name + ". Perhaps use a lower preference for this implementation?", coreException);
                thrownException = coreException;
            }
        }
        if (loaded == null && thrownException != null) {
            throw new CoreException("No services (of " + orderedServiceDelegator.size() + " loaders) were able to load the file.  Wrapping the last thrown exception", thrownException);
        }
        return loaded;
    }

    /**
     * Save an object with the preferred output mechanism.
     * The object is first converted by the {@link FileMarshalServiceRegistry}
     * @param saveMe the object to save
     * @param saveAsName the name to save it as
     * @param relativeToApplicationHome true if the file should be saved relative to application home
     * @return true if the file was saved
     * @throws CoreException if there is an error during marshalling of the object to the required content, or during the save operation
     */
    public boolean save(Object saveMe, String saveAsName, boolean relativeToApplicationHome) throws CoreException {
        File file = new File(saveAsName);
        FileOptions fileOptions = new DefaultFileOptions(file, false);

        OutputContentWrapper outputContentWrapper = FileMarshalServiceRegistry.getInstance().marshal(saveMe, fileOptions);
        return save(outputContentWrapper, saveAsName, relativeToApplicationHome);
    }

    /**
     * Save an object with the preferred output mechanism.
     * @param saveMe the object to save
     * @param saveAsName the name to save it as
     * @param relativeToApplicationHome true if the file should be saved relative to application home
     * @return true if the file was saved
     * @throws CoreException if there is an error during the save operation
     */
    public boolean save(OutputContentWrapper saveMe, String saveAsName, boolean relativeToApplicationHome) throws CoreException {
        boolean saved = false;
        CoreException thrownException = null;
        for (Iterator<IOService> it = orderedServiceDelegator.iterator(); it.hasNext() && !saved; ) {
            IOService service = it.next();
            logger.debug("Trying to save file {} using {} with priority {}.", saveAsName, service, service.order());
            try {
                saved = service.save(saveMe, saveAsName, relativeToApplicationHome);
            } catch (CoreException coreException) {
                logger.warn(service + " failed to save " + saveAsName + ". Perhaps use a lower preference for this implementation?", coreException);
                thrownException = coreException;
            }
        }
        if (!saved && thrownException != null) {
            throw new CoreException("No services (of " + orderedServiceDelegator.size() + " loaders) were able to save the file.  Wrapping the last thrown exception", thrownException);
        }
        return saved;
    }
}
