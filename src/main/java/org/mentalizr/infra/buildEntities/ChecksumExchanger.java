package org.mentalizr.infra.buildEntities;

import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChecksumExchanger {

    public static final Logger logger = LoggerFactory.getLogger(ChecksumExchanger.class.getSimpleName());

    public static void copyToContainer(String filename, String checksum) {
        logger.info("Write checksum file [" + filename + "] to tomcat container.");
        Path checksumFile = FileHelper.createM7rInfraTempFile(filename, checksum);
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            DockerCopy.copyFile(context, checksumFile, Const.CONTAINER_TOMCAT, "/");
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException("Exception on copying to container: " + e.getMessage(), e);
        }
    }

    public static String readFromContainer(String fileName) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        Path tempDir = FileHelper.createM7rInfraTempDir().asPath();
        try {
            DockerCopy.copyFileFromContainer(context, Const.CONTAINER_TOMCAT, "/" + fileName, tempDir);
        } catch (DockerExecutionException e) {
            String message = e.getMessage();
            if (message.contains("No such container:path:")
                    || message.contains("Could not find the file")) return "";
            throw new InfraRuntimeException("Exception on copying checksum file [" + fileName + "] from container: " + e.getMessage(), e);
        }
        try {
            String checksum = Files.readString(tempDir.resolve(fileName)).trim();
            logger.info("Read [" + fileName + "] with value [" + checksum + "] from tomcat container.");
            return checksum;
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on reading checksum from tempFile: " + e.getMessage(), e);
        }
    }

}
