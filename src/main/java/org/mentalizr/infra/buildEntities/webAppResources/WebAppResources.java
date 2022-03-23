package org.mentalizr.infra.buildEntities.webAppResources;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.connections.ConnectionTomcat;
import org.mentalizr.infra.buildEntities.initFiles.tomcat.ResrcChecksum;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.processExecutor.ProcessExecution;
import org.mentalizr.infra.processExecutor.ProcessExecutionException;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;
import org.mentalizr.infra.utils.FileHelper;
import org.mentalizr.infra.utils.M7rFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WebAppResources {

    private static final Logger logger = LoggerFactory.getLogger(WebAppResources.class.getSimpleName());

    public static void deploy() {
        TempDir tempDir = createTempDir();

        copyToTempDir(tempDir);
        createTar(tempDir);
        Path resrcChecksumFile = createChecksumFile(tempDir);
        if (deploymentRequired(resrcChecksumFile)) {
            logger.info("Deployment of resources is required.");
            copyResourcesToContainer(tempDir);
            copyChecksumFileToContainer(resrcChecksumFile);
        } else {
            logger.info("Deployment of resources is not required.");
        }
    }

    private static TempDir createTempDir() {
        try {
            return TempDirs.createUniqueTempDirAutoRemove(M7rHostTempDir.createInstance().asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException(e.getMessage(), e);
        }
    }

    private static boolean deploymentRequired(Path resrcChecksumFile) {
        String checksumOnTomcat = ConnectionTomcat.getResrcChecksum();
        if (checksumOnTomcat.equals("")) return true;
        try {
            String localChecksum = Files.readString(resrcChecksumFile).trim();
            return !localChecksum.equals(checksumOnTomcat);
        } catch (IOException e) {
            throw new InfraRuntimeException("Error on reading local checksum file: " + e.getMessage(), e);
        }
    }

    private static void copyChecksumFileToContainer(Path resrcChecksumFile) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            DockerCopy.copyFile(context,
                    resrcChecksumFile,
                    Const.CONTAINER_TOMCAT,
                    "/man/tomcat/webapps/resrc");
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException("Copying resource checksum file to tomcat failed: " + e.getMessage(), e);
        }
    }

    private static void copyResourcesToContainer(TempDir tempDir) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        String resourceArchiveFileName = new ResrcArchive(tempDir).getFileName();
        try {
            DockerCopy.copyFile(context,
                    new ResrcArchive(tempDir).asPath(),
                    Const.CONTAINER_TOMCAT,
                    "/");
            Docker.call(context,
                    "docker",
                    "exec",
                    Const.CONTAINER_TOMCAT,
                    "tar",
                    "xzf",
                    "/" + resourceArchiveFileName,
                    "-C", "/man/tomcat/webapps");
            Docker.call(context,
                    "docker",
                    "exec",
                    Const.CONTAINER_TOMCAT,
                    "rm",
                    "/" + resourceArchiveFileName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException("Copying resources to tomcat container failed: " + e.getMessage(), e);
        }
    }

    private static Path createChecksumFile(TempDir tempDir) {
        Path resrcPath = tempDir.asPath().resolve("resrc");
        try {
            List<Path> containedFiles = FileHelper.getContainingFiles(resrcPath);
            long crc32 = FileHelper.computeCrc32(containedFiles);
            logger.info("CRC32 checksum for all resources: " + crc32);
            return new ResrcChecksum(crc32).writeToHostTempDir();
        } catch (IOException e) {
            throw new InfraRuntimeException("Computing CRC32 checksum failed: " + e.getMessage(), e);
        }
    }

    private static void createTar(TempDir tempDir) {
        ResrcArchive resrcArchive = new ResrcArchive(tempDir);

        try {
            ProcessResultCollection result = ProcessExecution.execute(
                    logger,
                    ExecutionContext.isVerbose(),
                    "tar", "-C", tempDir.asPath().toAbsolutePath().toString(), "-czf", resrcArchive.toAbsolutePathString(), "resrc"
                    );
            if (result.isFail()) throw new InfraRuntimeException("Creating tar for resource temp file failed.");
        } catch (ProcessExecutionException e) {
            throw new InfraRuntimeException("Error on creating tar for resource temp file: " + e.getMessage(), e);
        }
    }

    private static void copyToTempDir(TempDir tempDir) {
        M7rFiles m7rFiles = new M7rFiles(tempDir);

        try {
            Files.createDirectories(tempDir.asPath().resolve("resrc/css"));
            Files.createDirectories(tempDir.asPath().resolve("resrc/js"));
            Files.createDirectories(tempDir.asPath().resolve("resrc/fonts"));
            Files.createDirectories(tempDir.asPath().resolve("resrc/img"));
            Files.createDirectories(tempDir.asPath().resolve("resrc/fontawesome/css"));
            Files.createDirectories(tempDir.asPath().resolve("resrc/bootstrap-icons"));
        } catch (IOException e) {
            throw new InfraRuntimeException("Could not create subdirs of temp resource dir: " + e.getMessage(), e);
        }

        m7rFiles.copy(
                "$M7R_FRONTEND$/node_modules/@fortawesome/fontawesome-free/css/all.min.css",
                "$TEMP_DIR$/resrc/fontawesome/css/fontawesome-free-all.min.css"
        );

        m7rFiles.copyDir(
                "$M7R_FRONTEND$/node_modules/@fortawesome/fontawesome-free/webfonts",
                "$TEMP_DIR$/resrc/fontawesome/webfonts"
        );

        m7rFiles.copy(
                "$M7R_FRONTEND$/node_modules/startbootstrap-sb-admin-2/css/sb-admin-2.min.css",
                "$TEMP_DIR$/resrc/css/"
        );

        m7rFiles.copyDir(
                "$INIT_RESRC$/img",
                "$TEMP_DIR$/resrc/img"
        );

        m7rFiles.copy(
                "$M7R_FRONTEND_PROJECT$/css/m7r-frontend.css",
                "$TEMP_DIR$/resrc/css"
        );

        m7rFiles.copy(
                "$M7R_FRONTEND_PROJECT$/css/m7r-fonts.css",
                "$TEMP_DIR$/resrc/css"
        );

        m7rFiles.copy(
                "$M7R_FRONTEND$/dist/m7r_spa.js",
                "$TEMP_DIR$/resrc/js"
        );

        m7rFiles.copy(
                "$M7R_FRONTEND$/dist/m7r_spa.js.map",
                "$TEMP_DIR$/resrc/js"
        );

        m7rFiles.copyDir(
                "$M7R_FRONTEND_PROJECT$/fonts",
                "$TEMP_DIR$/resrc/fonts"
        );

        m7rFiles.copy(
                "$M7R_WEB_COMPONENTS$/css/m7r-web-components.css",
                "$TEMP_DIR$/resrc/css"
        );

        m7rFiles.copy("$M7R_WEB_COMPONENTS$/fonts/m7r-icomoon.eot",
                "$TEMP_DIR$/resrc/fonts/"
        );

        m7rFiles.copy("$M7R_WEB_COMPONENTS$/fonts/m7r-icomoon.svg",
                "$TEMP_DIR$/resrc/fonts/"
        );

        m7rFiles.copy("$M7R_WEB_COMPONENTS$/fonts/m7r-icomoon.ttf",
                "$TEMP_DIR$/resrc/fonts/"
        );

        m7rFiles.copy("$M7R_WEB_COMPONENTS$/fonts/m7r-icomoon.woff",
                "$TEMP_DIR$/resrc/fonts/"
        );

        m7rFiles.copy(
                "$M7R_FRONTEND$/node_modules/bootstrap-icons/font/bootstrap-icons.css",
                "$TEMP_DIR$/resrc/bootstrap-icons/"
        );

        m7rFiles.copyDir(
                "$M7R_FRONTEND$/node_modules/bootstrap-icons/font/fonts",
                "$TEMP_DIR$/resrc/bootstrap-icons/fonts"
        );
    }

}
