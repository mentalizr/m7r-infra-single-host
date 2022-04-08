package org.mentalizr.infra.utils;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.InfraRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelper {

    public static Path writeToM7rInfraTempDir(String fileName, String content) throws IOException {
        M7rHostTempDir m7RHostTempDir = M7rHostTempDir.createInstance();
        if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(m7RHostTempDir.asPath());
        Path file = tempDir.asPath().resolve(fileName);
        Files.writeString(file, content);
        return file;
    }

    public static TempDir createM7rInfraTempDir() {
        M7rHostTempDir m7RHostTempDir = M7rHostTempDir.createInstance();
        try {
            if (!m7RHostTempDir.exists()) Files.createDirectories(m7RHostTempDir.asPath());
            return TempDirs.createUniqueTempDirAutoRemove(m7RHostTempDir.asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on creating temp dir: " + e.getMessage(), e);
        }
    }

    public static Path createM7rInfraTempFile(String filename, String content) {
        Path tempFile = createM7rInfraTempDir().asPath().resolve(filename);
        try {
            return Files.writeString(tempFile, content);
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on creating temp checksum file: " + e.getMessage(), e);
        }
    }

}
