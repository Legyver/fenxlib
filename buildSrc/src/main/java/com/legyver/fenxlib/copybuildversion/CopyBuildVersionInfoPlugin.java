package com.legyver.fenxlib.copybuildversion;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

import java.io.*;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class CopyBuildVersionInfoPlugin implements Plugin<Project> {
    private static final Logger logger = Logging.getLogger(CopyBuildVersionInfoPlugin.class);

    @Override
    public void apply(Project project) {
        // register the plugin extension as 'copyVersionInfo {}' configuration block
        String version = (String) project.getVersion();


        File projectDir = project.getProjectDir();
        Path projectDirPath = projectDir.toPath();
        Path modulePath = projectDirPath.resolve("src/main/java/module-info.java");

        String moduleName;
        Path targetPath;
        try (Scanner scanner = new Scanner(modulePath)) {
            moduleName = new ModuleInfoProcessor(scanner).getModuleName();
            String moduleNameAsPath = moduleName.replaceAll("\\.", "/");
            targetPath = projectDirPath.resolve("src/main/resources/" + moduleNameAsPath + "/version/version.properties");
            File parentDir = targetPath.toFile().getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (OutputStream outputStream = new FileOutputStream(targetPath.toFile())) {
            logger.info("Project [{}] Copying version [{}] to {}", project.getName(), version, targetPath);
            Properties properties = new Properties();
            properties.setProperty(moduleName, version);
            properties.store(outputStream, "Generated by CopyBuildVersionInfoPlugin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
