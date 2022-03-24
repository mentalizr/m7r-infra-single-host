package org.mentalizr.infra.buildEntities.webAppResources;

import de.arthurpicht.utils.io.tempDir.TempDir;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.InfraRuntimeException;
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

public class WebAppResourcesHelper {

    private static final Logger logger = LoggerFactory.getLogger(WebAppResourcesHelper.class.getSimpleName());

    public static String computeChecksum(TempDir tempDir) {
        Path resrcPath = tempDir.asPath().resolve("resrc");
        try {
            List<Path> containedFiles = FileHelper.getContainingFiles(resrcPath);
            long crc32 = FileHelper.computeCrc32(containedFiles);
            logger.info("CRC32 checksum for all resources: " + crc32);
            return Long.toString(crc32);
        } catch (IOException e) {
            throw new InfraRuntimeException("Computing CRC32 checksum failed: " + e.getMessage(), e);
        }
    }

    public static ResrcArchive createTar(TempDir tempDir) {
        ResrcArchive resrcArchive = new ResrcArchive(tempDir);

        try {
            ProcessResultCollection result = ProcessExecution.execute(
                    logger,
                    ExecutionContext.isVerbose(),
                    "tar", "-C", tempDir.asPath().toAbsolutePath().toString(), "-czf", resrcArchive.toAbsolutePathString(), "resrc"
                    );
            if (result.isFail()) throw new InfraRuntimeException("Creating tar for resource temp file failed.");
            return resrcArchive;
        } catch (ProcessExecutionException e) {
            throw new InfraRuntimeException("Error on creating tar for resource temp file: " + e.getMessage(), e);
        }
    }

    public static void copyToTempDir(TempDir tempDir) {
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
