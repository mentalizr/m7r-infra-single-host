package org.mentalizr.infra.utils;

import de.arthurpicht.utils.io.nio2.FileUtils;
import de.arthurpicht.utils.io.tempDir.TempDir;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.webAppResources.SubstitutorConfigurationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class M7rFiles {

    private static final Logger logger = LoggerFactory.getLogger(M7rFiles.class.getSimpleName());
    private final StringSubstitutorConfiguration configuration;

    public M7rFiles(TempDir tempDir) {
        this.configuration = SubstitutorConfigurationFactory.create(tempDir);
    }

    public void copy(String source, String destination) {
        String expandedSource = StringSubstitutor.substitute(source, this.configuration);
        String expandedDestination = StringSubstitutor.substitute(destination, this.configuration);

        logger.info("Copy file [" + expandedSource + "] to [" + expandedDestination + "].");

        Path sourcePath = Paths.get(expandedSource);
        if (!FileUtils.isExistingRegularFile(sourcePath))
            throw new IllegalArgumentException("File not found: [" + sourcePath.toAbsolutePath() + "].");

        Path destinationPath = Paths.get(expandedDestination);
        if (FileUtils.isExistingDirectory(destinationPath)) {
            Path filename = sourcePath.getFileName();
            destinationPath = destinationPath.resolve(filename);
        } else {
            Path parentPath = destinationPath.getParent();
            if (!FileUtils.isExistingDirectory(parentPath))
                throw new IllegalArgumentException("Directory not found: [" + parentPath.toAbsolutePath() + "].");
        }

        try {
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            throw new InfraRuntimeException("Error on copying file: " + e.getMessage());
        }
    }

    public void copyDir(String source, String destination) {
        String expandedSource = StringSubstitutor.substitute(source, this.configuration);
        String expandedDestination = StringSubstitutor.substitute(destination, this.configuration);

        logger.info("Copy dir [" + expandedSource + "] to [" + expandedDestination + "].");

        Path sourcePath = Paths.get(expandedSource);
        Path destinationPath = Paths.get(expandedDestination);
        try {
            FileUtils.copyDirectory(sourcePath, destinationPath);
        } catch (IOException e) {
            throw new InfraRuntimeException("Error on copying directory: " + e.getMessage());
        }
    }

//    public static void copyDirContent(String source, String destination) {
//        Path sourcePath = Paths.get(source);
//        Path destinationPath = Paths.get(destination);
//        try {
//            FileHelper.copyDirectoryContent(sourcePath, destinationPath);
//        } catch (IOException e) {
//            throw new InfraRuntimeException("Error on copying file: " + e.getMessage());
//        }
//    }

}
