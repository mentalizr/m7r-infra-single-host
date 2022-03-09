package org.mentalizr.infra.buildEntities;

import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogDirs {

    public static String getLogDir() {
        return System.getProperty("user.home") + "/m7r/logs";
    }

    public static String getMongoLogDir() {
        return getLogDir() + "/mongo";
    }

    public static String getTomcatLogDir() {
        return getLogDir() + "/tomcat";
    }

    public static boolean existsAllLogDirs() {
        Path mongoLogDir = Paths.get(getMongoLogDir());
        Path tomcatLogDir = Paths.get(getTomcatLogDir());
        return FileUtils.isExistingDirectory(mongoLogDir) && FileUtils.isExistingDirectory(tomcatLogDir);
    }

    public static void createAllLogDirs() {
        Path mongoLogDir = Paths.get(getMongoLogDir());
        Path tomcatLogDir = Paths.get(getTomcatLogDir());

        try {
            Files.createDirectories(mongoLogDir);
            Files.createDirectories(tomcatLogDir);
        } catch (IOException e) {
            throw new InfraRuntimeException("Logfiles could not be created: " + e.getMessage(), e);
        }
    }

}
