package org.mentalizr.infra.buildEntities.html;

import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlChecksum {

    private static final Logger logger = LoggerFactory.getLogger(HtmlChecksum.class.getSimpleName());
    private static final String fileName = "html.checksum";

    public static String forHtmlFiles() {
        HtmlFiles htmlFiles = HtmlFiles.getInstance();
        return Long.toString(htmlFiles.getChecksum());
    }

    public static void writeToContainer() {
        HtmlFiles htmlFiles = HtmlFiles.getInstance();
        long checksum = htmlFiles.getChecksum();
        Path tempDir = createTempDir();
        Path tempChecksumFile = createTempChecksumFile(tempDir, checksum);
        logger.info("Writing html.checksum [" + checksum + "] to tomcat container.");
        copyToContainer(tempChecksumFile);
    }

    public static String readFromContainer() {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        Path tempDir = createTempDir();
        try {
            DockerCopy.copyFileFromContainer(context, Const.CONTAINER_TOMCAT, "/" + fileName, tempDir);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException("Exception on copying from container: " + e.getMessage(), e);
        }
        try {
            String checksum = Files.readString(tempDir.resolve(fileName)).trim();
            logger.info("Read html.checksum [" + checksum + "] from tomcat container.");
            return checksum;
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on reading checksum from tempFile: " + e.getMessage(), e);
        }
    }

    private static void copyToContainer(Path checksumFile) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            DockerCopy.copyFile(context, checksumFile, Const.CONTAINER_TOMCAT, "/");
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException("Exception on copying to container: " + e.getMessage(), e);
        }
    }

    private static Path createTempDir() {
        try {
            return TempDirs.createUniqueTempDirAutoRemove(M7rHostTempDir.createInstance().asPath()).asPath();
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on creating temp dir: " + e.getMessage(), e);
        }
    }

    private static Path createTempChecksumFile(Path tempDir, long checksum) {
        Path checksumFile = tempDir.resolve(fileName);
        try {
            return Files.writeString(checksumFile, Long.toString(checksum));
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on creating temp checksum file: " + e.getMessage(), e);
        }
    }

}
