package com.legyver.fenxlib.core.io;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.load.ApplicationHome;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.io.IOService;
import com.legyver.fenxlib.api.io.content.*;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionActionDecorator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Service to handle reading/writing files
 */
public class DiskFileIoService implements IOService {
    private static final Logger logger = LogManager.getLogger(DiskFileIoService.class);

    @Override
    public int order() {
        return 0;
    }

    @Override
    public InputStream loadInputStream(String appName, String name, boolean relativeToApplicationHome) throws CoreException {
        if (relativeToApplicationHome) {
            ApplicationHome applicationHome = ApplicationContext.getApplicationHome();
            if (applicationHome == null) {
                applicationHome = new ApplicationHome(appName);
                ApplicationContext.setApplicationHome(applicationHome);
            }
            File appHomeDir = applicationHome.getAppHome();
            Path homeDirPath = appHomeDir.toPath();
            Path filePath = homeDirPath.resolve(name);
            File file = filePath.toFile();

            return new ExceptionToCoreExceptionActionDecorator<InputStream>(
                    () -> file.exists() ? FileUtils.openInputStream(file) : null
            ).execute();
        }
        return null;
    }

    @Override
    public boolean save(OutputContentWrapper contentWrapper, String saveAsName, boolean relativeToApplicationHome) throws CoreException {
        File file;
        if (relativeToApplicationHome) {
            ApplicationHome applicationHome = ApplicationContext.getApplicationHome();
            if (applicationHome == null) {
                logger.error("No Application home set.");
                throw new CoreException("Unable to save file " + saveAsName + " to application home: ApplicationHome is null");
            }
            File appHomeDir = applicationHome.getAppHome();
            Path homeDirPath = appHomeDir.toPath();
            Path filePath = homeDirPath.resolve(saveAsName);
            file = filePath.toFile();
        } else {
            file = new File(saveAsName);
        }

        return new ExceptionToCoreExceptionActionDecorator<>(() -> {
            Boolean success = true;
            if (contentWrapper instanceof StringContentWrapper) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                    IOUtils.write(((StringContentWrapper) contentWrapper).getContent(), bufferedOutputStream, StandardCharsets.UTF_8);
                }
            } else if (contentWrapper instanceof ByteArrayContentWrapper) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                    IOUtils.write(((ByteArrayContentWrapper) contentWrapper).getContent(), bufferedOutputStream);
                }
            } else if (contentWrapper instanceof SerializedContentWrapper) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
                    objectOutputStream.writeObject(contentWrapper.getContent());
                }
            } else if (contentWrapper instanceof PipeContentWrapper) {
                Pipe.SourceChannel sourceChannel = ((PipeContentWrapper) contentWrapper).getContent().source();
                ByteBuffer buffer = ByteBuffer.allocate(((PipeContentWrapper) contentWrapper).getBufferSize());

                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                    while (sourceChannel.read(buffer) > 0) {
                        //limit is set to current position and position is set to zero
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            bufferedOutputStream.write(buffer.get());
                        }
                        //position is set to zero and limit is set to capacity to clear the buffer.
                        buffer.clear();
                    }

                }
            } else {
                logger.warn("Unable to output file to using specified content wrapper: " + contentWrapper);
                success = false;
            }
            return success;
        }).execute();
    }

}
